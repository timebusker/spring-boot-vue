<template>
  <el-container style="width: 100%;height: 0;padding-bottom: 53%;background: white;flex-wrap: wrap;overflow: auto">
    <el-row style="width: 100%;height: 200px;margin: 10px">
      <el-col :span="20">
        <el-carousel :interval="4000" type="card" height="200px">
          <el-carousel-item v-for="item in 6" :key="item">
            <img src="http://12.12.12.3:8080/mine/M00/00/00/DAwMA1ytfiyAX7q8AAF_FweSshA687.png" style="width: 100%"/>
          </el-carousel-item>
        </el-carousel>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" style="margin-left: 20px" @click="outerVisible = true">上传<i class="el-icon-upload el-icon--right"></i></el-button>
      </el-col>
      <el-dialog title="上传图片" :visible.sync="outerVisible" :center="false" width="30%;height:200px" :before-close="handleClose">
        <el-upload
          :file-list="fileList"
          action="/api/file/single-image"
          list-type="picture-card"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove">
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="innerVisible">
          <img width="100%" :src="innerVisible" alt="">
        </el-dialog>
      </el-dialog>
    </el-row>
    <el-row style="width: 100%;">
      <el-col :span="4" v-for="(o, index) in 12" :key="o" :offset="index > 0 ? 2 : 0">
        <el-card :body-style="{ padding: '0px' }">
          <img src="http://12.12.12.3:8080/mine/M00/00/00/DAwMA1ytfiyAX7q8AAF_FweSshA687.png" class="image">
          <div style="padding: 14px;">
            <span>好吃的汉堡</span>
            <div class="bottom clearfix">
              <el-button type="text" class="button">操作按钮</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
  import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
  export default {
    components: {ElButton},
    data: function () {
      return {
        outerVisible: false,
        dialogImageUrl: '',
        innerVisible: false,
        fileList: []
      }
    },
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      }
    }
  }
</script>

<style lang="stylus" scoped>

</style>
