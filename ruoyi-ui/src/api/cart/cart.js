import request from '@/utils/request'

// 查询购物车信息管理列表
export function listCart(query) {
  return request({
    url: '/cart/cart/list',
    method: 'get',
    params: query
  })
}
// 查询用户购物车列表
export function getMyCart(query) {
  return request({
    url: '/cart/cart/getMyCart',
    method: 'get',
    params: query
  })
}
// 查询购物车信息管理详细
export function getCart(id) {
  return request({
    url: '/cart/cart/' + id,
    method: 'get'
  })
}

// 新增购物车信息管理
export function addCart(data) {
  return request({
    url: '/cart/cart',
    method: 'post',
    data: data
  })
}
// 用户加入购物车功能
export function userAddCart(userId,bookId,choosenum) {
  return request({
    url: '/cart/cart/userAddCart',
    method: 'post',
    data: {
      userId:userId,
      bookId:bookId,
      count:choosenum
    }
  })
}
// 用户修改购物车数量
export function updateCartCount(id,count) {
  return request({
    url: '/cart/cart/updateCartCount/'+id+'/'+count,
    method: 'put',
  
  })
}
// 修改购物车信息管理
export function updateCart(data) {
  return request({
    url: '/cart/cart',
    method: 'put',
    data:data
  })
}
// 用户删除购物车信息
export function delCartById(id) {

  return request({
    url: '/cart/cart/delCartById/' + id,
    method: 'delete'
  })
}

// 用户结算完成 将购物车信息转换为订单信息
export function addOrderByPayed(ids,count,price) {

  return request({
    url: '/cart/cart/addOrderByPayed/' + ids+'/'+count+'/'+price,
    method: 'delete'
  })
}
// 删除购物车信息管理
export function delCart(id) {
  return request({
    url: '/cart/cart/' + id,
    method: 'delete'
  })
}
