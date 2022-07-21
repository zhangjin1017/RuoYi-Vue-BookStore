<template>
  <div>
    <div class="bookinfodiv" v-for="item in orderList" :key="item.id">
      <div class="bookinfo" v-for="item2 in item.bookInfoList" :key="item2.id">
        <image-preview class="image-preview" :src="item2.picture" :width="100" :height="100" />X{{ item2.total }}
        <h3 class="bookName">{{ item2.bookName }}</h3>

        <span class="author">作者:{{ item2.author }} </span>
        <span class="price">￥{{ item2.price }}</span>
      </div>

      <div class="countandpriceanddel">
        <div>
          <span class="count">共{{ item.count }}本</span>
          <!-- 换行 -->
          <br />
          <span class="book-price">￥{{ item.price.toFixed(2) }}</span>
        </div>
        <!-- 删除 -->
      <div class="bookdel">
        <el-button type="danger" icon="el-icon-delete" @click="delCart(item.id)"></el-button>
      </div>
      <!-- 支付时间 -->
      <div class="paytime">
        <span>{{ item.payTime }}</span>
        </div>
      </div>
      
    </div>
  </div>
</template>

<script>
import { getMyOrder } from '@/api/order/order'
export default {
  data() {
    return {
      orderList: [],
    }
  },
  created() {
    this.getMyOrder()
  },
  methods: {
    getMyOrder() {
      getMyOrder().then((res) => {
        this.orderList = res.rows
        console.log(this.orderList)
      })
    },
  },
}
</script>

<style scoped>
.bookinfodiv {
  /* 订单信息边框 */
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
  padding: 10px;

  display: flex;
  justify-content: flex-start;
  /* justify-content: space-between; */
  align-items: center;
  margin-bottom: 10px;
}
.bookinfo {
 
  width: 200px;

  align-items: center;
  margin-bottom: 10px;
  /* 换行 */
  word-wrap: break-word;
  word-break: break-all;
}
.bookName {
  font-size: 12px;
  font-weight: bold;
}
.author {
  font-size: 12px;
  color: #ccc;
}
.price {
  font-size: 12px;
  color: red;
}
.countandpriceanddel {
  margin-left:auto;
  
  align-items: center;
  margin-right: 10px;
}
.bookdel {
  
 flex: end;
}
.paytime {
  /* 父级div的右下角 */
  position:relative;
  top: 50px;
  font-size: 12px;
  color: #ccc;



}

</style>
