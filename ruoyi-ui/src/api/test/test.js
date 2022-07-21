import request from '@/utils/request'

// 查询测试列表
export function listTest(query) {
  return request({
    url: '/test/test/list',
    method: 'get',
    params: query
  })
}

// 查询测试详细
export function getTest(id) {
  return request({
    url: '/test/test/' + id,
    method: 'get'
  })
}

// 新增测试
export function addTest(data) {
  return request({
    url: '/test/test',
    method: 'post',
    data: data
  })
}

// 修改测试
export function updateTest(data) {
  return request({
    url: '/test/test',
    method: 'put',
    data: data
  })
}

// 删除测试
export function delTest(id) {
  return request({
    url: '/test/test/' + id,
    method: 'delete'
  })
}
