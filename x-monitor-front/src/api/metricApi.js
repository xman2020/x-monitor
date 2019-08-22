import { axios } from '@/utils/request'

export default {
  metricAdd (parameter) {
    return axios({
      url: 'http://localhost:8000/test.html',
      method: 'post',
      data: parameter
    })
  }
}
