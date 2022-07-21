<template>
  <div>
    <el-row>
      <el-col :span="6" v-for="book in bookList" :key="book.id">
        <el-card :body-style="{ padding: '0px' }" id="el-card" shadow="never">
          <image-preview class="image-preview" :src="book.picture" />
          <div style="display: block">
            <h3>{{ book.bookName }}</h3>
            <br />
            <span>{{ book.author }}</span>
            <span class="book-price">￥{{ book.price }}</span>

            <div class="bottom clearfix">
              <el-input-number class="el-input-number" v-model="book.choosenum" @change="handleChange" :min="1" :max="book.total" label="数量"></el-input-number>
              <el-button class="addCart" type="primary" @click="addToCart(book)">加入购物车</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { userAddCart } from '@/api/cart/cart'
export default {
  props: {
    userId: {
      type: Number,
      default: 0
    },
    bookList: {
      required: true,
      type: Array,
    },
  },
  data() {
    return {

    }
  },
  created() {
    console.log(this.bookList)
  },
  mounted() {
    this.bookList.forEach((e)=>{
      e['choosenum']=1
    })

  },
  methods: {
    handleChange(val) {
      console.log(val)
    },
    addToCart(book) {
      console.log(this.userId)
      console.log(book.id)
      console.log(book.choosenum)
      if(book.choosenum==undefined){
        this.$alert('请选择加购数量', '提示', {
          confirmButtonText: '确定',
        });
      }else{

    
      userAddCart(this.userId, book.id, book.choosenum).then((response) => {
        console.log("response:"+response)
        this.$message({
          message: '加入购物车成功',
          type: 'success'
        })
      })
  }

    },
  },
}
</script>

<style scoped>
#el-card {
  display: flex;
  height: 50%;
}
.el-input-number {
  float: left;
  width: 50%;
}
.image-preview {
  padding: 10px;
  width: 300px;
  height: 300px;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}

.clearfix:after {
  clear: both;
}

.book-price {
  float: right;
  font-weight: bold;
  color: red;
  font-size: 20px;
}
.addCart {
  background-color: red;
  float: right;
}
</style>
