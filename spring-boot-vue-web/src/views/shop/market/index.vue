<template>
  <div class="container">
    <div class="search">
      <span class="iconfont icon-iconfontsousuokuangsousuo" style="font-size:22px"></span>
      <input></input>
      <button>全站搜索</button>
    </div>
    <!--vue awesome swiper轮播图使用-->
    <!--npm install vue-awesome-swiper --save-dev-->
    <swiper :options="swiperOption" ref="imageSwiper" class="header-swiper">
      <swiper-slide>
        <img src="http://12.12.12.3:8080//mine/M00/00/00/DAwMA1yu17SAPmmYAACy8L6JKAs995.jpg" class="image"></img>
      </swiper-slide>
      <swiper-slide>
        <img src="http://12.12.12.3:8080//mine/M00/00/00/DAwMA1yu18CABA3JAABrfg2UjHw036.jpg" class="image"></img>
      </swiper-slide>
      <swiper-slide>
        <img src="http://12.12.12.3:8080//mine/M00/00/00/DAwMA1yu2GeAQCoqAABKyCnTBHc435.jpg" class="image"></img>
      </swiper-slide>
      <swiper-slide>
        <img src="http://12.12.12.3:8080//mine/M00/00/00/DAwMA1yxdcOADm4WAACZI74tAHg820.jpg" class="image"></img>
      </swiper-slide>
      <!--分页滑动等操作暂无-->
    </swiper>
    <!--秒杀商品列表-->
    <div class="goods-main">
      <swiper :options="goodsOption" ref="goodsSwiper" style="height: auto">
        <swiper-slide v-for="(item,index) in list" :index="index" v-if="index<3" :key="item.id">
          <div class="goods">
            <img :src="item.image" width="90px"/>
            <div style="margin-top:10px;font-size: 12px;">
              <span style="font-weight: bold;width: 50%;display: inline-block;text-align: left">{{item.name}}</span>
              <span style="font-weight: bold;color: red;width: 40%;display: inline-block;text-align: right">￥ {{item.price}}</span>
            </div>
            <div style="margin-top:10px;text-align: right">
              <span style="font-size: 12px;font-weight: bold;color: red;width: 100%;">3天12时34分12秒</span>
            </div>
          </div>
          <div class="goods">
            <img :src="item.image" width="90px"/>
            <div style="margin-top:10px;font-size: 12px;">
              <span style="font-weight: bold;width: 50%;display: inline-block;text-align: left">{{item.name}}</span>
              <span style="font-weight: bold;color: red;width: 40%;display: inline-block;text-align: right">￥ {{item.price}}</span>
            </div>
            <div style="margin-top:10px;text-align: right">
              <span style="font-size: 12px;font-weight: bold;color: red;width: 100%;">3天12时34分12秒</span>
            </div>
          </div>
        </swiper-slide>
      </swiper>
      <div style="height: auto;overflow-x: hidden;overflow-y:auto;margin-top: 10px;background: white;width: 100%">
        <div style="display: flex;height: 100px;width: 100%" v-for="(item,index) in list" :index="index" :key="item.id">
          <div style="padding: 2px;">
            <img :src="item.image" width="90px" height="80px">
          </div>
          <!--flex:1;/*这里设置为占比1，填充满剩余空间*/ -->
          <div style="flex: 1;text-align: left;padding: 5px;color:blue;font-size: 14px;font-weight: 200;text-decoration:underline">
            {{item.title}}
          </div>
          <div style="margin-right: 3px;text-align: right;width: 120px">
            <span style="display: block;margin: 5px">{{item.name}}</span>
            <span style="display: block;margin: 5px">￥ {{item.price}}</span>
            <span style="display: block;margin: 5px">{{ item.endTime}}</span>
            <a style="display: block;margin: 5px">
              <button style="border-radius: 10px;width: 80px;background: red;color: white;padding: 1px">立即秒杀</button>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import 'swiper/dist/css/swiper.css'
  import {swiper, swiperSlide} from 'vue-awesome-swiper'
  import {diffTime} from '../../../utils/DateUtil'
  
  export default {
    components: {
      swiper,
      swiperSlide
    },
    data: function () {
      return {
        swiperOption: {
          loop: true,
          mousewheelControl: true,
          autoplayDisableOnInteraction: false,
          observer: true,
          autoplay: true,
          speed: 800
        },
        goodsOption: {
          // 每行显示三个，显示两行
          slidesPerView: 3,
          slidesPerColumn: 2,
          slidesPerGroup: 6,
          loopedSlides: 6,
          // 行排列展示
          slidesPerColumnFill: "row",
          loop: true,
          mousewheelControl: true,
          autoplayDisableOnInteraction: false,
          observer: true,
          autoplay: true,
          speed: 800
        },
        params: {},
        list: [],
        diff: ""
      }
    },
    mounted: function () {
      // current swiper instance
      // 然后你就可以使用当前上下文内的swiper对象去做你想做的事了
      this.imageSwiper.slideTo(1, 800, false)
      this.goodsSwiper.slideTo(1, 800, false)
    },
    computed: {
      imageSwiper() {
        return this.$refs.imageSwiper.swiper
      },
      goodsSwiper() {
        return this.$refs.goodsSwiper.swiper
      }
    },
    created: function () {
      this.queryGoods();
    },
    methods: {
      queryGoods: function () {
        this.postRequest("/shop/goods/list", this.params).then(response => {
          if (response.data.code === 0) {
            this.list = response.data.list;
            this.params = response.data.params;
          }
        }).catch(error => {
          console.log(error)
        });
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .container
    width 100vw
    height auto
    background gray
    overflow hidden
  
  .search
    display flex
    flex-wrap nowrap
    justify-content space-around
    line-height 30px
    background white
    border-radius 10px
    padding 5px 10px
    input
      margin 0 5px
      font-size 18px
    button
      border-radius 5px
      background #3a8ee6
      color white
      padding 3px 5px
      width 80px
  
  .header-swiper
    width 100vw
    height 150px
    img
      width inherit
      height inherit
  
  .goods-main
    height auto
    display flex
    flex-wrap wrap
    justify-content space-between
    overflow auto
    .goods
      font-size 14px
      width 110px
      height 110px
      margin 3px
      padding 1px
      border-radius 3px
      background #8cc5ff
</style>
