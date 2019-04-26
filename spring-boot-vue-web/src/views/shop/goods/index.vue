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
          <el-form-item label="秒杀时间" prop="rangeTime">
            <el-date-picker value-format="yyyy-MM-dd HH:mm:ss" style="width: 100%" v-model="goods.rangeTime" type="datetimerange"
                            range-separator="至" start-placeholder="秒杀开始时间" end-placeholder="秒杀结束时间"></el-date-picker>
          </el-form-item>
          <el-form-item label="商品图片" prop="image">
            <el-upload class="avatar-uploader" accept="image/png, image/jpeg" action="/file/tmp/" align="left" :show-file-list="false"
                       :on-preview="handlePreview" :on-remove="handleRemove" :on-success="handleSuccess">
              <img v-if="goods.image" width="80px" :src="goods.image">
              <i v-else class="el-icon-plus" style="background: #b4bccc;width: 80px;line-height: 60px"></i>
            </el-upload>
            <el-dialog :visible.sync="isPreview">
              <img width="100%" :src="imageUrl" alt="商品图片">
            </el-dialog>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveEdit('goods')">确 定</el-button>
      </span>
      </el-dialog>
    </el-row>
    <el-row style="width: 100%">
      <el-table :data="list" style="width: 100%" :height="tableHeight" :stripe="true" border :show-overflow-tooltip="true" :highlight-current-row="true" size="small">
        <el-table-column label="编号" prop="id"></el-table-column>
        <el-table-column label="图片" prop="image">
          <template slot-scope="scope">
            <img width="80px" :src="scope.row.image">
          </template>
        </el-table-column>
        <el-table-column label="名称" prop="name"></el-table-column>
        <!--内容过多时，进行隐藏-->
        <el-table-column label="标题" prop="title" show-overflow-tooltip="true"></el-table-column>
        <el-table-column label="价格" prop="price"></el-table-column>
        <el-table-column label="库存" prop="stock"></el-table-column>
        <el-table-column label="开始时间" prop="startTime"></el-table-column>
        <el-table-column label="结束时间" prop="endTime"></el-table-column>
        <el-table-column label="编辑">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" round size="mini" @click="handleEdit(scope.row)"></el-button>
            <el-button type="warning" icon="el-icon-delete" round size="mini" @click="handleDelete(scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row style="width: 100%;height: 40px;line-height: 40px">
      <el-pagination @size-change="handlePageChange" @current-change="handleCurrentPage" :current-page="1"
                     :page-sizes="[100, 200, 300, 400]" :page-size="100" layout="total, sizes, prev, pager, next, jumper" :total="400"
                     style="float: right;margin-bottom: 30px;margin-top: 10px;margin-right: 80px">
      </el-pagination>
    </el-row>
  </el-container>
</template>

<script>
  export default {
    data: function () {
      return {
        isAdd: false,
        tableHeight: (window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 180,
        dialogVisible: false,
        isPreview: false,
        imageUrl: '',
        timeRange: [],
        list: [],
        goods: {},
        validate: {
          name: [
            {required: true, message: '请输入商品名称', trigger: 'blur'},
            {min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'blur'}
          ],
          title: [
            {required: true, message: '请输入商品标题', trigger: 'blur'},
            {min: 1, max: 32, message: '长度在 1 到 32 个字符', trigger: 'blur'}
          ],
          stock: [
            {required: true, message: '请输入商品库存量', trigger: 'change'}
          ],
          price: [
            {required: true, message: '请输入商品价格', trigger: 'change'}
          ],
          rangeTime: [
            {required: true, message: '请选择商品参与秒杀的时间范围', trigger: 'blur'}
          ]
        },
        params: {}
      }
    },
    computed: {},
    // 使用监听器监听数据变化，完成异步数据更新;
    watch: {
      dialogVisible: function (newVisible) {
        if (false === newVisible) {
          this.goods = {};
        } else if (true === newVisible) {
          if (JSON.stringify(this.goods) != "{}") {
            this.isAdd = false;
          } else {
            this.isAdd = true;
          }
        }
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
      },
      handleDelete: function () {
      
      },
      handleEdit: function (item) {
        console.log(item)
        if (null !== item) {
          this.goods = item;
        }
        this.dialogVisible = true;
      },
      handleClose: function (done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      saveEdit: function (goods) {
        this.$refs[goods].validate((valid) => {
          if (valid) {
            this.putRequest("/shop/goods/save", this.goods).then(response => {
              if (response.data.code === '0') {
                this.dialogVisible = false;
                this.$message({
                  message: '商品保存成功！',
                  type: 'success'
                });
              }
            }).catch(error => {
              console.log(error)
              this.$message({
                message: '商品保存失败！',
                type: 'error'
              });
            })
          } else {
            return false;
          }
        })
      },
      handlePreview: function () {
        this.isPreview = true;
      },
      handleRemove: function () {
        this.goods.image = "";
      },
      handleSuccess: function (response, file) {
        this.imageUrl = file.url;
        this.goods.image = response.file.url;
      },
      handleCurrentPage: function () {
      
      },
      handlePageChange: function () {
      
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
