import Vue from 'vue'
import Vuex from 'vuex'
import jwtDecode from 'jwt-decode'
import axios from 'axios'

Vue.use(Vuex)

const session = window.sessionStorage

export default new Vuex.Store({
  state: {
    scroll: 0,
    isEnd: false,
    user: null,
    nickName : null,
    userItemList: null,
    wearItem: null
  },
  mutations: {
    SCROLLED(state, payload){
      state.scroll = payload
    },
    ARRIVED(state, payload){
      state.isEnd = payload
    },
    SETUSER(state, payload){
      session.setItem('user', payload)
      state.user = JSON.parse(payload)
    },
    GETUSER(state, user){
      state.user = user
    },
    CLEARUSER(state){
      state.user = null
    },
    USER_ITEM_LIST(state, data){
      state.userItemList = data
    },
    CHANGE_ITEM(state, item){
      const items = [...state.userItemList]
      console.log(items)
      console.log(item)
      items.forEach(ele => {
        if (ele.itemCategories == item.itemCategories){
          if (ele.itemNo == item.itemNo){
            ele.wearFlag = 1
          }
          else {
            ele.wearFlag = 0
          }
        }
      })
      state.userItemList = items
      this.commit('USER_WEAR_ITEM')
    },
    USER_WEAR_ITEM(state){
      const temp = ['itemCloth', 'itemHair', 'itemBackground', 'itemDeco']
      const result = {itemBackground : null, itemCloth : null, itemHair : null, itemDeco : null}
      state.userItemList.forEach(ele => {
        if (ele.wearFlag == 1){
          const tar = temp[Number(ele.itemCategories)]
          result[tar] = ele.itemImage
        }
      })
      state.wearItem = result
    }
  },
  actions: {
    scrollHandler({ commit }, payload){
      commit('SCROLLED', payload)
    },
    arrivedEnd({ commit }, payload){
      commit('ARRIVED', payload)
    },
    setUser({commit}, credentials){
      const { authorization } = credentials
      commit('SETUSER', JSON.stringify(jwtDecode(authorization)))
    },
    getUser({commit}){
      const session = window.sessionStorage
      try {
        const user = JSON.parse(session.getItem('user'))
        commit('GETUSER', user)
        return new Promise(resolve => resolve(user))
      }
      catch {
        return
      }
    },
    clearUser({ commit }){
      commit('CLEARUSER')
    },
    userCharData({commit},itemList){
      commit('USER_CHAR_DATA',itemList)
    },
    /// 모든 아이템 정보 불러오기 ///
    userItemList({commit}){
      axios({
        methods: 'GET',
        url: `https://k6e105.p.ssafy.io:8080/api/item/info/${this.state.user.doll_no}`,
        headers:{
          Authorization: sessionStorage.getItem('accessToken'),
          Refresh_Authorization: sessionStorage.getItem('refreshToken')
        }
      })
      .then(res => commit('USER_ITEM_LIST', res.data))
      .finally(() => commit('USER_WEAR_ITEM'))
    },

    changeItem({commit}, item){
      axios({
        method: 'put',
        url: 'https://k6e105.p.ssafy.io:8080/api/useritem/wear',
        data: {
          dollNo: this.state.user.doll_no,
          itemNo: item.itemNo
        },
        headers:{
          Authorization: sessionStorage.getItem('accessToken'),
          Refresh_Authorization: sessionStorage.getItem('refreshToken')
        }
      })
      .then(() => commit('CHANGE_ITEM', item))
    },
    userItemWear({commit},data){
      commit('USER_WEAR_ITEM',data)
      console.log(data)
    }
  }
})
