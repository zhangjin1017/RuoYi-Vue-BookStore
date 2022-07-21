<template>
  <div>
    <!-- <h1>我要买书 <button @click="showUser">我的id</button></h1> -->
    <!-- 左侧书籍列表-->
    <el-tabs tab-position="left" style="height: 100%" @tab-click="changeTab">
      <el-tab-pane label="所有书籍">
        <!-- 商品卡片 -->
        <BookCard :bookList="bookList" :userId="userId" name="111"></BookCard>
        <!-- 商品卡片 end-->
      </el-tab-pane>
      <el-tab-pane v-for="item in classList" :key="item.id" :label="item + '类'">
        <!-- 商品卡片 -->
        <BookCard :bookList="bookList" :userId="userId"></BookCard>
        <!-- 商品卡片 end-->
      </el-tab-pane>
    </el-tabs>
    <!-- 左侧书籍列表 end-->
  </div>
</template>

<script>
import BookCard from '@/components/BookCard'
import { getUserProfile } from '@/api/system/user'
import { getAllBook, getAllClass } from '@/api/book/book'
export default {
  data() {
    return {
      userId: '',
      bookList: [],
      classList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 8,
        bookClass: null,
      },
    }
  },
  created() {
    this.getUser()
    this.getAllBooks()
    this.getAllClass()
  },
  methods: {
    getUser() {
      getUserProfile().then((response) => {
        this.userId = response.data.userId
      })
    },
    getAllBooks() {
      getAllBook(this.queryParams).then((response) => {
        this.bookList = response.rows
      })
    },
    getAllClass() {
      getAllClass().then((response) => {
        this.classList = response
      })
    },
    showUser() {
      console.log(this.userId)
    },
    changeTab(tab) {
      this.queryParams.bookClass = this.classList[tab.index - 1]
      console.log(tab.index, this.classList[tab.index - 1])
      getAllBook(this.queryParams).then((response) => {
        this.bookList = response.rows
      })
    },
  },
  components: {
    BookCard,
  },
}
</script>

<style></style>
