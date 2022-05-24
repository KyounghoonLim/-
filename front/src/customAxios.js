import axios from 'axios'
import store from './store'

store.getters.config

const session = window.sessionStorage

export const URL = 'https://k6e105.p.ssafy.io:8080'

const CustomAxios = axios.create({
  baseURL: URL,
})

CustomAxios.interceptors.request.use(
  function CustomInterceptorRequest(config){ 
    const temp = {...config}
    temp.headers.Authorization = session.getItem('accessToken')
    temp.headers.Refresh_Authorization = session.getItem('refreshToken')

    return temp
  }
)

CustomAxios.interceptors.response.use(
  function CustomInterceptorSucced(res){

    if (res.headers.authorization) {
      window.sessionStorage.setItem('accessToken', res.headers.authorization)
      store.dispatch('setUser', res.headers)
    }
    if (res.headers.refresh_authorization){
      window.sessionStorage.setItem('refreshToken', res.headers.refresh_authorization)
    }

    return res
  },
  /// API 처리에서 401 에러는 토큰 인증에 실패했을 경우에만 반환하게 엄격하게 관리해야함!! ///
  function CustomInterceptorError(err){
    if (err.toJSON().status === 401){
      session.clear()
    }

    return Promise.reject(err.toJSON());
  }
)

export default CustomAxios
