<template>
  <el-container style="width: 100%;height: 0;padding-bottom: 53%;background: white;flex-wrap: wrap;overflow: auto">
    <el-row style="width: 100%;height: 600px;margin: 50px 20px">
      <el-col :span="20">
        <el-carousel :interval="4000" type="card">
          <el-carousel-item v-for="item in list" :key="item.id">
            <img :src="item.url" style="width: 100%"/>
          </el-carousel-item>
        </el-carousel>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" style="margin-left: 20px" @click="outerVisible = true">上传<i class="el-icon-upload el-icon--right"></i></el-button>
      </el-col>
      <el-dialog title="上传图片" :visible.sync="outerVisible" :center="false" width="30%;height:200px" :before-close="handleClose">
        <el-upload
          :auto-upload="autoUpload"
          name="image"
          :limit="9"
          list-type="picture-card"
          accept="image/png, image/jpeg"
          action="/api/file/single-image"
          :on-preview="handlePreview"
          :on-change="handleChange"
          :on-remove="handleRemove"
          :on-exceed="handleExceed"
          v-show="(uploadList.length <=10)">
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="innerVisible">
          <img width="100%" :src="innerVisible" alt="">
        </el-dialog>
      </el-dialog>
    </el-row>
    <el-row style="width: 100%;">
      <el-col :span="6" v-for="(item, index) in list" :key="item.id" style="margin: 30px;justify-content: center">
        <el-card :body-style="{ padding: '0px',width: '450px'}">
          <img :src="item.url" width="100%">
          <div style="padding: 14px;">
            <el-tag type="primary">{{item.userId}}</el-tag>
            <el-tag type="danger">{{item.createTime}}</el-tag>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </el-container>
</template>

<script>
  import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
  import {getFileMD5} from '../../../utils/FileUtil'
  
  export default {
    components: {ElButton},
    data: function () {
      return {
        outerVisible: false,
        dialogImageUrl: '',
        innerVisible: false,
        uploadList: [],
        autoUpload: true,
        list: []
      }
    },
    created: function () {
      this.queryAllImage()
    },
    methods: {
      queryAllImage(){
        this.getRequest("/api/file/list").then((response => {
            this.list = response.data.res;
          })
        ).catch(error => {
          console.log(error)
        })
      },
      queryImage(value){
        this.getRequest("/api/file/image/" + value).then((response => {
            return response.data.image;
          })
        ).catch(error => {
          console.log(error)
        })
      },
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
      handlePreview(file) {
        this.dialogImageUrl = file.url;
        this.innerVisible = true;
      },
      handleChange (file, fileList) {
        let _this = this;
        getFileMD5(file, function (md5) {
          console.log("==============>", md5)
          let image = _this.queryImage(md5);
          if (image != undefined && JSON.stringify(image) != "{}") {
		    // 设置图片是否自动上传
            _this.autoUpload = false;
          } else {
		    // 设置图片是否自动上传
            _this.autoUpload = true;
          }
        })
      },
      handleExceed(files, fileList){
        console.log(fileList)
        this.$message.error("一次上传的文件太多啦！");
      }
    }
  }
</script>

<style lang="stylus" scoped>

</style>
