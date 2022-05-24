<template>
  <div id="calendar-container">
    <calendar is-expanded trim-weeks :max-date="new Date()"
      :attributes="calendarAttrs" ref="calendar"
      @update:from-page="updateFromPage"
      />
  </div>
</template>

<script>
import Calendar from 'v-calendar/lib/components/calendar.umd'
// import Dummy from './Dummy.js'
import customAxios from '@/customAxios'

const VCalendar =  {
  data: () => {
    return {
      calendarAttrs: [{
        key: 'today',
        highlight: true,
        dates: new Date(),
      }],
      items: null,
      flag: false,
      clickedDay: null
    }
  },
  props: {
    changes: Boolean
  },
  components: {
    Calendar,
  },
  methods: {
    getItems: function(init=true){
      customAxios({
      method: 'get',
      url: `/api/diary/findDate/${this.page[0] + '/' + this.page[1]}`
    })
    .then(res => {
      this.items = res.data
    })
    .then(() => this.loadDairies(init))
    .catch(err => console.log(err))
    },

    makeDisabled: function(){
      const notInMonth = document.querySelectorAll('.is-not-in-month')
      notInMonth.forEach(ele => {
        ele.classList.add('day-disabled')
      })

      const disalbled = document.querySelectorAll('.is-disabled')
      disalbled.forEach(ele => {
        let tar = ele.parentElement
        tar.classList.add('day-disabled')
      })
    },

    createButton: function(){
      const todayCol = document.querySelectorAll(`.id-${this.today[0]}-${this.today[1]}-${this.today[2]}`)[0]

      if (todayCol){
        const button = document.createElement('button')
        button.id = 'create-btn'

        button.addEventListener('click', () => {
          this.$router.push('/diary')
        })
        todayCol.appendChild(button)
      }
    },

    randomColor: function(){
      return '#' + Math.floor(Math.random() * 0xffffff).toString(16)
    },

    loadDairies: function(init=true){
      this.$nextTick(
        function(){
          const parent = document.querySelectorAll('.vc-day')[0]
          const w = parent.clientWidth * 0.5

          for (const key in this.items){
            if (key.slice(0, 7) == `${this.page[0]}-${this.page[1]}`){
              const tar = document.querySelectorAll(`.id-${key}`)[0]
              let wrapper

              if (init){
                wrapper = document.createElement('div')
                wrapper.setAttribute('class', 'diary-wrapper')
              }
              else {
                for (const child of tar.childNodes.entries()){
                  if (child[1].classList[0] == 'diary-wrapper'){
                    wrapper = child[1]
                    while (wrapper.firstChild){
                      wrapper.removeChild(wrapper.firstChild)
                    }
                    break
                  }
                }
              }

              for (const item of this.items[key]){
                const idx = this.items[key].indexOf(item)
                if ((idx+1) * 40 + 16 < w){
                  const p = document.createElement('p')
                  p.setAttribute('class', 'diary-title')
                  // 랜덤 색상 부여
                  // p.setAttribute('style', `background-color: ${this.randomColor()}`)
                  p.innerText = item.title
                  wrapper.appendChild(p)
                }
                else {
                  break
                }
              }
              const dot = document.createElement('span')
              dot.setAttribute('class', 'is-exist')
              const helpMessage = document.createElement('p')
              helpMessage.setAttribute('class', 'diary-help-message')
              helpMessage.innerText = `작성된 일기 수: ${this.items[key].length}개`

              dot.appendChild(helpMessage)
              wrapper.appendChild(dot)
              tar.appendChild(wrapper)
            }
          }
          this.makeDisabled()
          this.createButton()
          this.setEvent()
        }
      )
      this.$emit('change-diaries', false)
    },

    clickEventHandler: function(e, tar){
      if (e.target.id != 'create-btn'){
        const key = tar.slice(3, )
        this.$emit('show-date-diary', {date: key, diaries: this.items[key]})
      }
    },

    setEvent: function(){
      const dayList = document.querySelectorAll('.vc-day')
      dayList.forEach(ele => {
        if (ele.classList[ele.classList.length - 1] != 'day-disabled'){
          ele.addEventListener('click', e => this.clickEventHandler(e, ele.classList[1]))
        }
      })
    },

    updateFromPage: function(){
      this.loadDairies()
    }
  },

  computed: {
    page: function(){
      const time = this.$refs.calendar._data
      let y = String(time.pages[0].year); let m = String(time.pages[0].month); let d = String(time.focusableDay);

      if (m.length == 1){
        m = '0' + m
      }
      if (d.length == 1){
        d = '0' + d
      }

      return [y, m, d]
    },

    today: function(){
      const today = new Date()
      let y = String(today.getFullYear()); let m = String(today.getMonth()+1); let d = String(today.getDate());

      if (m.length == 1){
        m = '0' + m
      }
      if (d.length == 1){
        d = '0' + d
      }

      return [y, m, d]
    }
  },

  watch: {
    changes: function(){
      if (this.changes){
        this.getItems(false)
      }
    }
  },

  mounted: function(){
    this.getItems()
    const _this = this
    window.addEventListener('resize', function(){
      if (_this.flag){
        clearTimeout()
        return
      }
      else {
        _this.flag = true
        setTimeout(function(){
          _this.loadDairies(false)
          _this.flag = false
        }, 1000)
      }
    })
  },
}

export default VCalendar
</script>

<style lang="scss">
  #calendar-container {
    width: 100%;
    padding: 5rem;

    .vc-container {
      width: 100%;

      .vc-pane-container, .vc-pane-layout, .vc-pane, .vc-headr, .vc-weeks {
        width: 100%;
      }

      .vc-day {
        width: 100%;
        min-width: 32px;
        aspect-ratio: 1/1;
        padding: 0.5rem;
        flex-direction: column;
        justify-content: flex-start;
        align-items: flex-start;
        background-color: white;
        cursor: pointer;

        @keyframes scale {
          from {
            transform: scale(1);
            box-shadow: none;
          }
          to {
            transform: scale(1.05);
            box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
          }
        }

        &:hover {
          animation: scale 0.2s ease-in forwards;
          z-index: 2;
        }


        .vc-day-layer {
          width: 100%;
          height: 100%;
          justify-content: flex-start;
          align-items: flex-start;
          padding: 0.5rem;
        }

        button {
          font-size: 1.25rem;
          font-weight: bold;
          border-radius: 10px;
          border: 1px #93D9CE solid;
          color: #93D9CE;
          background-color: white;
          box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
          margin: auto auto 0;
          padding: 0.125rem;
          width: 80%;
          position: absolute;
          bottom: 5%;
          left: 10%;
          cursor: pointer;

          &::after {
            content: '+';
          }

          &:hover {
            background-color: #93D9CE;
            color: white;
          }
        }
      }
      
      .day-disabled {
        cursor: initial;

        &:hover {
          animation: none;
        }
      }

      .diary-wrapper {
        width: 100%;
        height: 50%;
        position: relative;

        .diary-title {
          width: 100%;
          max-width: 150px;
          text-overflow: ellipsis;
          white-space: nowrap;
          overflow: hidden;
          margin: 0.25rem 0;
          padding: 0.375rem;
          color: white;
          font-weight: bold;
          box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
          background-color: #93D9CE;
        }

        .is-exist {
          position: absolute;
          bottom: 0;
          width: 10px;
          aspect-ratio: 1/1;
          background-color: #FED771;
          border-radius: 50%;

          @keyframes showing {
            from {opacity: 0;}
            to {opacity: 1;}
          }

          .diary-help-message {
            background-color: white;
            color: #777777;
            box-shadow: 1px 2px 4px rgba(0, 0, 0, 0.35);
            border-radius: 10px;
            word-break: keep-all;
            white-space: nowrap;
            font-size: 0.75rem;
            margin: 0;
            padding: 1rem;
            position: absolute;
            visibility: hidden;
            z-index: 2;
          }

          &:hover {
            .diary-help-message {
              animation: showing 0.3s ease-in forwards;
              visibility: visible;
            }
          }
        }
      }
    }
  }
</style>