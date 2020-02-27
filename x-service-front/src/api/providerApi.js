import mock from 'mockjs2'
import { axios } from '@/utils/request'

const getProviderUrl = '/provider/getProvider'
const saveProviderUrl = '/provider/saveProvider'

export default {
  getProvider (parameter) {
    return axios({
      url: getProviderUrl,
      method: 'post',
      data: parameter
    })
  },
  saveProvider (parameter) {
    return axios({
      url: saveProviderUrl,
      method: 'post',
      data: parameter
    })
  }
}

mock.mock(/\/getProvider/, 'post', {
  success: true,
  errorCode: null,
  message: null,
  data: {
    id: 123,
    name: 'metric',
    desc: '指标的描述',
    status: 'ENABLE',
    type: 'HTTP',
    path: '/metric',
    loadBalance: 'WEIGHT',
    nodes: [
      {
        ip: '172.22.0.1',
        port: 8080,
        path: '/metric',
        weight: 20
      },
      {
        ip: '172.22.0.2',
        weight: 10
      }
    ]
  }
})

mock.mock(/\/saveProvider/, 'post', {
  success: true,
  errorCode: null,
  message: null
})
