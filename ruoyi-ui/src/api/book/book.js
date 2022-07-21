import request from '@/utils/request'

// 查询图书信息列表
export function listBook(query) {
  return request({
    url: '/book/book/list',
    method: 'get',
    params: query
  })
}
// 会员获取图书信息列表
export function getAllBook(query) {
  return request({
    url: '/buyBook/list',
    method: 'get',
    params: query
  })
}
// 会员获取图书信息列表
export function getAllClass(query) {
  return request({
    url: '/buyBook/getAllClass',
    method: 'get',
    params: query
  })
}

// 查询图书信息详细
export function getBook(id) {
  return request({
    url: '/book/book/' + id,
    method: 'get'
  })
}

// 新增图书信息
export function addBook(data) {
  return request({
    url: '/book/book',
    method: 'post',
    data: data
  })
}

// 修改图书信息
export function updateBook(data) {
  return request({
    url: '/book/book',
    method: 'put',
    data: data
  })
}

// 删除图书信息
export function delBook(id) {
  return request({
    url: '/book/book/' + id,
    method: 'delete'
  })
}
