<template>
  <el-container class="container">
    <el-row style="width: 100%;height: 40px">
      <el-col :span="12" style="padding: 5px;">
        <el-input placeholder="请输入内容检索！" prefix-icon="el-icon-search"></el-input>
      </el-col>
      <el-col :span="3" style="padding: 5px;line-height:40px">
        <el-button size="small" type="primary" round @click="dialogVisible = true">新增商品</el-button>
      </el-col>
      <el-dialog :title="isAdd ? '新增商品' : '编辑商品'" :visible.sync="dialogVisible" width="45%" :before-close="handleClose">
        <el-form ref="goods" :model="goods" label-width="100px" :rules="validate" size="small">
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="goods.name" placeholder="商品名称"></el-input>
          </el-form-item>
          <el-form-item label="商品标题" prop="title">
            <el-input v-model="goods.title" placeholder="商品标题"></el-input>
          </el-form-item>
          <el-form-item label="商品库存量" prop="stock">
            <el-input-number v-model="goods.stock" placeholder="商品库存量" style="float: left;width: 40%" :min="1" :max="999"></el-input-number>
          </el-form-item>
          <el-form-item label="商品价格" prop="price">
            <el-input-number v-model="goods.price" :precision="2" placeholder="商品价格" style="float: left;width: 40%" :min="0.01" :max="99999999"></el-input-number>
          </el-form-item>
          <el-form-item label="秒杀时间" prop="killTime">
            <el-date-picker @change="handleSelectTime" value-format="yyyy-MM-dd HH:mm:ss" style="width: 100%" v-model="killTime" type="datetimerange"
                            range-separator="至" start-placeholder="秒杀开始时间" end-placeholder="秒杀结束时间"></el-date-picker>
          </el-form-item>
          <el-form-item label="商品图片" prop="image">
            <el-upload class="avatar-uploader" accept="image/png, image/jpeg" action="/file/single-image" align="left" :show-file-list="false">
              <img v-if="goods.image" width="80px" :src="goods.image">
              <i v-else class="el-icon-plus" style="background: #b4bccc;width: 80px;line-height: 60px"></i>
            </el-upload>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
      </el-dialog>
    </el-row>
    <el-row style="width: 100%;">
    
    </el-row>
  
  </el-container>
</template>

<script>
  export default {
    data: function () {
      return {
        isAdd: false,
        tableHeight: (window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 252,
        dialogVisible: false,
        killTime: "",
        goods: {},
        validate: {
          name: [
            {required: true, message: '请输入商品名称', trigger: 'blur'},
            {min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'blur'}
          ],
          title: [
            {required: true, message: '请输入商品名称', trigger: 'blur'},
            {min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'blur'}
          ],
          stock: [
            {required: true, message: '请输入商品库存量', trigger: 'blur'}
          ],
          price: [
            {required: true, message: '请输入商品价格', trigger: 'blur'}
          ]
        }
      }
    },
    // 使用监听器监听数据变化，完成异步数据更新;
    watch: {
      dialogVisible: function (newVisible, oldVisible) {
        if (false === newVisible) {
          this.menu = {};
        } else if (true === newVisible) {
          if (JSON.stringify(this.menu) != "{}") {
            this.isAdd = false;
          } else {
            this.isAdd = true;
          }
        }
      }
    },
    methods: {
      handleEdit: function (item) {
        if (null !== item) {
          this.menu = item;
        }
        this.dialogVisible = true;
      },
      handleClose: function (done) {
        var _this = this;
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      saveEdit: function () {
        this.postRequest("/menu/save", this.menu).then(response => {
          this.dialogVisible = false;
          this.$message({
            message: '菜单保存成功！',
            type: 'success'
          });
        }).catch(error => {
          this.$message({
            message: '菜单保存失败！',
            type: 'error'
          });
        });
      },
      handleSelectTime: function (value) {
        console.log(value)
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .container
    width 100%
    height 100%
    display flex
    flex-wrap wrap
    background white
  
  .avatar-uploader
    width 80px
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
</style>
