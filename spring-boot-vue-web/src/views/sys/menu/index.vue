<template>
  <el-container class="container">
    <el-row style="width: 100%;height: 50px;background: grey;">
      <el-col :span="12" style="padding: 5px;">
        <el-input placeholder="请输入内容检索！" prefix-icon="el-icon-search"></el-input>
      </el-col>
      <el-col :span="3" style="padding: 5px;line-height:40px">
        <el-button size="small" type="primary" round @click="handleEdit(null)">新增菜单</el-button>
      </el-col>
    </el-row>
    <el-row style="width: 100%;margin-top: 10px">
      <el-table :data="list" style="width: 100%" :height="tableHeight" :stripe="true" border :show-overflow-tooltip="true" :highlight-current-row="true" size="small" row-key="id" tooltip-effect="dark">
        <el-table-column label="编号" prop="id" width="180"></el-table-column>
        <el-table-column label="名称" prop="name"></el-table-column>
        <el-table-column label="外部链接" prop="isFrame" width="70">
          <template slot-scope="scope">
            <el-tag>{{scope.row.isFrame === 0 ? '否' : '是' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="图标" prop="icon" width="70">
          <template slot-scope="scope">
            <span class="iconfont" :class="scope.row.icon"></span>
          </template>
        </el-table-column>
        <el-table-column label="Url连接" prop="url"></el-table-column>
        <el-table-column label="Vue组件" prop="component"></el-table-column>
        <el-table-column label="排序号" prop="sort" width="70">
          <template slot-scope="scope">
            <el-tag>{{scope.row.sort}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" prop="createTime" width="120"></el-table-column>
        <el-table-column label="编辑">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" round size="mini" @click="handleEdit(scope.row)"></el-button>
            <el-button type="warning" icon="el-icon-delete" round size="mini" @click="handleDelete(scope.row)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="1"
                     :page-sizes="[100, 200, 300, 400]" :page-size="100" layout="total, sizes, prev, pager, next, jumper" :total="400"
                     style="float: right;margin-bottom: 30px;margin-top: 10px;margin-right: 80px">
      </el-pagination>
    </el-row>
    <el-dialog :title="isAdd ? '新增菜单' : '编辑菜单'" :visible.sync="dialogVisible" width="45%" :before-close="handleClose">
      <el-form ref="menu" :model="menu" label-width="80px" :rules="validateMenu" size="small">
        <el-form-item label="名称" prop="name">
          <el-input v-model="menu.name" placeholder="菜单名称"></el-input>
        </el-form-item>
        <el-form-item label="外部链接">
          <el-switch v-model="menu.isFrame" style="justify-content:center;float: left"></el-switch>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="menu.icon" placeholder="iconfont的16进制图标"></el-input>
        </el-form-item>
        <el-form-item label="URL连接" prop="url">
          <el-input v-model="menu.url" placeholder="URL连接"></el-input>
        </el-form-item>
        <el-form-item label="Vue组件" prop="component">
          <el-input v-model="menu.component" placeholder="Vue组件"></el-input>
        </el-form-item>
        <el-form-item label="排序号">
          <el-input-number style="float:left;width:50%" v-model="menu.sort" :step="1" :min="0" :max="999" placeholder="排序号" size="small"></el-input-number>
        </el-form-item>
        <el-form-item label="父级菜单" prop="pid">
          <el-cascader style="float:left;width:50%" :change-on-select="true" v-model="menu.pid" :show-all-levels="false" :options="list" :props="props" @change="handleSelectChange" placeholder="父级菜单"></el-cascader>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveEdit">确 定</el-button>
      </span>
    </el-dialog>
  </el-container>
</template>

<script>
  import ElButton from "../../../../node_modules/element-ui/packages/button/src/button";
  import ElInput from "../../../../node_modules/element-ui/packages/input/src/input";
  import ElCol from "element-ui/packages/col/src/col";
  export default{
    components: {
      ElCol,
      ElInput,
      ElButton
    },
    data: function () {
      return {
        isAdd: false,
        menu: {},
        list: [],
        tableHeight: (window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight) - 252,
        dialogVisible: false,
        props: {
          value: 'id',
          label: 'name',
          children: 'children'
        },
        // 约定表单校验规则
        validateMenu: {
          name: [
            {required: true, message: '请输入菜单名称', trigger: 'blur'},
            {min: 3, max: 5, message: '长度在 1 到 32 个字符', trigger: 'blur'}
          ],
          url: [
            {required: true, message: '请输入菜单URL地址', trigger: 'blur'}
          ],
          component: [
            {required: true, message: '请输入组件地址及名称', trigger: 'blur'}
          ],
          pid: [
            {required: true, message: '请选择活父级菜单', trigger: 'blur'}
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
    created: function () {
      this.queryMenu();
    },
    compute: {},
    methods: {
      queryMenu: function () {
        var _this = this
        this.getRequest("/api/menu/list", _this.menu).then(response => {
          _this.list = response.data.list;
        }).catch(error => {
          console.log(error)
        })
      },
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
        var _this = this;
        _this.postRequest("/api/menu/save", _this.menu).then(response => {
          _this.dialogVisible = false;
          _this.$message({
            message: '菜单保存成功！',
            type: 'success'
          });
          _this.queryMenu();
        }).catch(error => {
          _this.$message({
            message: '菜单保存失败！',
            type: 'error'
          });
        });
      },
      handleDelete: function (item) {
        var _this = this;
        this.$confirm('确认关闭？').then(_ => {
          _this.deleteRequest("/api/menu/delete", item).then(response => {
            _this.$message({
              message: '菜单删除成功！',
              type: 'success'
            });
            _this.queryMenu();
          }).catch(error => {
            _this.$message({
              message: '菜单删除失败！',
              type: 'error'
            });
          });
        })
      },
      handleSelectChange: function (value) {
        var len = value.length;
        console.log(value, "----->", len, "----->", value[len - 1]);
        // this.menu.pid= parseInt(value[len - 1] + "");
        this.menu.pid = value[len - 1] + "";
        console.log(this.menu.pid);
      },
      // 分页组件
      handleSizeChange: function () {
      
      },
      // 分页组件
      handleCurrentChange: function () {
      
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
</style>
