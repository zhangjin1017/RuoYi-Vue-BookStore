<template>
  <div class="div11">
  
    <div v-if="cartList !==undefined && cartList != null  && cartList.length > 0"> 
    <div  class="bookinfodiv" v-for="item in cartList" :key="item.id">
      <!-- 单选按钮 -->
      <el-switch class="el-switch" v-model="item.ischoose" active-color="#13ce66" inactive-color="grey"> </el-switch>
      <!-- 图书信息 -->
      <image-preview class="image-preview" :src="item.bookInfo.picture" :width="100" :height="100" />
      <div class="bookinfo">
        <h3>{{ item.bookInfo.bookName }}</h3>
        <span>{{ item.bookInfo.author }}</span>
        <span class="book-price">￥{{ item.bookInfo.price.toFixed(2) }}</span>
      </div>
      <div class="countanddel">
      <!-- 加减 -->
      <el-input-number class="el-input-number" v-model="item.count" @change="countChange(item.bookId)" :min="1" :max="item.bookInfo.total" label="数量"></el-input-number>

      <!-- 删除 -->
      <div class="bookdel">
        <el-button type="danger" icon="el-icon-delete" @click="delCart(item.id)"></el-button>
      </div>

      </div>
    </div>


    </div>
    <div v-else class="listisnull">
      空空如也
      <!-- 去购物 -->
      <div class="go-shopping">
        <el-link type="info" @click="goShopping">去购物</el-link>
         
        </div>
      </div>
    <div class="footer-container">
    <!-- 左侧的全选 -->
    <div class="custom-control custom-checkbox">
      <input type="checkbox" class="custom-control-input" id="cbFull" :checked="checked" @change="fullChange" />
      <label class="custom-control-label" for="cbFull">全选</label>
    </div>

    <!-- 中间的合计 -->
    <div>
      <span>合计：</span>
      <span class="total-price">{{ total_price.toFixed(2) }}</span>
    </div>

    <!-- 结算按钮 -->
    <button type="button" class="btn btn-primary btn-settle" @click="pay">结算（{{ total_count }}）</button>
  </div>
  </div>
</template>

<script>
import { getUserProfile } from '@/api/system/user'
import { getMyCart,updateCartCount,delCartById,addOrderByPayed } from '@/api/cart/cart'
export default {
  data() {
    return {
      userId: '',
      cartList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      checked: false,
      total: 0,
      price: 0,
    }
  },
  computed: {
    total_count() {
      let total_count = 0
      this.cartList.forEach((e) => {
        if (e.ischoose) {
          total_count += e.count
        }
      })
      this.count=total_count
      return total_count
    },
    total_price() {
      let total_price = 0
      this.cartList.forEach((e) => {
        if (e.ischoose) {
          total_price += e.count * e.bookInfo.price
        }
      })
      this.price=total_price
      return total_price
    }
  },
  created() {
    this.getUser()
    this.getMyCarts()
  },
  mounted() {
    this.cartList.forEach((e) => {
      e['ischoose'] = false
    })
  },
  watch: {
    cartList: {
      handler(val) {
        this.checked = true
        val.forEach((e) => {
          if (!e.ischoose) {
            this.checked = false
          }
        })
      },
      deep: true
    }
  },
  methods: {
    getUser() {
      getUserProfile().then((response) => {
        this.userId = response.data.userId
      })
    },
    getMyCarts() {
      getMyCart(this.queryParams).then((response) => {
        this.cartList = response.rows
      })
    },
    countChange(bookId) {
      this.cartList.forEach((e) => {
        if (e.bookId == bookId) {
          e.count = e.count
          console.log(e.count, bookId)
          updateCartCount(e.bookId, e.count).then(() => {
            // this.getMyCarts()
          })
        }
      })
      

      
    },
    fullChange() {
      this.checked = !this.checked
      this.cartList.forEach((e) => {
        e['ischoose'] = this.checked
      })
    },
    delCart(id) {
     delCartById(id).then(() => {
       this.getMyCarts()
      //  删除成功
      this.$message({
          message: '删除成功',
          type: 'success'
        })
     })
      
    },
    goShopping() {
      this.$router.push({
        path: '/bookstore/buybook'
      })
    },
    pay() {
      // 获取所有选中的购物车id
      let cartIds = []
      this.cartList.forEach((e) => {
        if (e.ischoose) {
          cartIds.push(e.id)
        }
      })
      //模拟支付
      this.$message({
        message: '支付成功',
        type: 'success'
      })
      // 清空购物车
      //订单生成
      addOrderByPayed(cartIds,this.count,this.price).then((response) => {
        this.getMyCarts()
      })


    }
  },
}
</script>

<style scoped>
.div11{
  /* 可以滚动的div */
  overflow-y: scroll;
  height: 500px;
}
.listisnull{
  text-align: center;
  margin-top: 50px;
  font-size: 20px;
}
.bookinfodiv {
  border: 1px solid #ccc;
  /* 居中 */
  display: flex;
  /* justify-content: center; */
  align-items: center;
}
.el-switch {
  margin-left: 20px;
}
.bookinfo{
  width: 200px;
}
.image-preview {
  margin-left: 100px;
  margin-right: 100px;
}
.book-price {
  
  float: right;
  font-weight: bold;
  color: red;
  font-size: 20px;
}
.countanddel {
  display: flex;
  justify-content: space-between;
  align-items: center;
  
}
.el-input-number {
  margin-left: 50px;
  width: 120px;
  margin-left: 500px;
}
.bookdel{
  padding-left: 100px;
}

.footer-container {
  font-size: 20px;
  height: 100px;
  width: 100%;
  border-top: 1px solid #efefef;
  position: fixed;
  bottom: 0;
  background-color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
}

.custom-checkbox {
  display: flex;
  align-items: center;
}

#cbFull {
  margin-right: 5px;
}

.btn-settle {
  background-color:red;
  height: 80%;
  min-width: 150px;
  border-radius: 25px;
  font-size: 20px;
  margin-right: 250px;
}

.total-price {
  font-weight: bold;
  font-size: 20px;
  color: red;
}
</style>
