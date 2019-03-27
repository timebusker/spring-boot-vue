<template>
  <el-container class="container">
    <el-table :data="list" style="width: 100%" :stripe="true" :highlight-current-row="true">
      <el-table-column type="expand">
        <template slot-scope="scope">
          <!--隐藏内置表头-->
          <el-table :data="scope.row.children" style="width: 100%;" :show-header="false" :highlight-current-row="true">
            <el-table-column label="编号" prop="id"></el-table-column>
            <el-table-column label="名称" prop="name"></el-table-column>
            <el-table-column label="图标" prop="icon" width="80">
              <template slot-scope="scope">
                <span class="iconfont">&{{scope.row.icon | encodeFilter}}</span>
              </template>
            </el-table-column>
            <el-table-column label="Url连接" prop="url"></el-table-column>
            <el-table-column label="Vue组件" prop="component"></el-table-column>
            <el-table-column label="排序号" prop="sort" width="80">
              <template slot-scope="scope">
                <el-tag>{{scope.row.sort}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="创建时间" prop="createTime"></el-table-column>
            <el-table-column label="编辑">
              <template slot-scope="scope">
                <el-button type="warning" icon="el-icon-edit" round size="mini" @click="handleEdit(scope.row)"></el-button>
                <el-button type="danger" icon="el-icon-delete" round size="mini"></el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column label="编号" prop="id"></el-table-column>
      <el-table-column label="名称" prop="name"></el-table-column>
      <el-table-column label="图标" prop="icon" width="80">
        <template slot-scope="scope">
          <span class="iconfont">&{{scope.row.icon}}</span>
        </template>
      </el-table-column>
      <el-table-column label="Url连接" prop="url"></el-table-column>
      <el-table-column label="Vue组件" prop="component"></el-table-column>
      <el-table-column label="排序号" prop="sort" width="80">
        <template slot-scope="scope">
          <el-tag>{{scope.row.sort}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" prop="createTime"></el-table-column>
      <el-table-column label="编辑">
        <template slot-scope="scope">
          <el-button type="warning" icon="el-icon-edit" round size="mini" @click="handleEdit(scope.row)"></el-button>
          <el-button type="danger" icon="el-icon-delete" round size="mini" @click="handleDelete(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="编辑菜单" :visible.sync="dialogVisible" width="35%" :before-close="handleClose">
      <el-form ref="menu" :model="menu" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="menu.name" placeholder="菜单名称"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="menu.icon" placeholder="iconfont的16进制图标"></el-input>
        </el-form-item>
        <el-form-item label="URL连接">
          <el-input v-model="menu.url" placeholder="URL连接"></el-input>
        </el-form-item>
        <el-form-item label="Vue组件">
          <el-input v-model="menu.component" placeholder="Vue组件"></el-input>
        </el-form-item>
        <el-form-item label="排序号">
          <el-input v-model="menu.sort" placeholder="排序号"></el-input>
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
  import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
  export default{
    components: {ElButton},
    name: 'AdminMenu',
    data: function () {
      return {
        menu: {},
        list: [],
        dialogVisible: false
      }
    },
    created: function () {
      this.queryMenu();
    },
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
        this.menu = item
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
      }
    }
  }
</script>

<style lang="stylus" scoped>
  .container
    width 100%
    height 100%
    background red
</style>
