<template>
  <v-content>
    <v-container>
      <v-row style="padding: 12px;">
        <el-button type="primary" @click="handleAdd()">Add user</el-button>
      </v-row>
      <v-row>
        <v-col cols="12">
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column fixed prop="userId" label="USER_ID" width="150">
            </el-table-column>
            <el-table-column prop="userName" label="USER_NAME" width="150">
            </el-table-column>
            <el-table-column prop="roleName" label="ROLE_NAME" width="150">
            </el-table-column>
            <el-table-column prop="phone" label="PHONE" width="150">
            </el-table-column>
            <el-table-column prop="email" label="EMAIL" min-width="200">
            </el-table-column>
            <el-table-column fixed="right" label="option" width="100">
              <template slot-scope="scope">
                          <el-button @click="handleClick(scope.row)" type="text" size="small">edit</el-button>
                          <el-button @click="handleDelete(scope.row)" type="text" size="small">delete</el-button>
</template>
    </el-table-column>
  </el-table>
        </v-col>
      </v-row>
      <v-dialog width="500" v-model="dialog">
        <v-card>
          <v-card-title>
            <span class="headline">Detail</span>
            <v-spacer></v-spacer>
          </v-card-title>
          <v-card-text>
          <el-form ref="form" :model="selected" label-width="120px">
            <el-form-item label="name">
              <el-input v-model="selected.name"></el-input>
            </el-form-item>
            <el-form-item label="phone">
              <el-input v-model="selected.phone"></el-input>
            </el-form-item>
            <el-form-item label="email">
              <el-input v-model="selected.email"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">confirm</el-button>
              <el-button @click="dialog = false">cancel</el-button>            
            </el-form-item>
          </el-form>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-container>
  </v-content>
</template>

<script>
  export default {
    name: 'User', // 用户管理
  
    components: {
      // LeafletMap
    },
  
    data: () => ({
      id: '',
      isEdit: false,
      dialog: false,
      selected: {
        name: '',
        phone: '',
        email: ''
      },
      tableData: []
    }),
    computed: {},
  
    created() {
      this.handleQuery();
    },
  
    mounted() {},
  
    methods: {
  
      handleQuery() {
        const url = '/icbc/hzyjs/user/query';
        this.$axios.get(url).then(
          response => {
            this.tableData = response.data.data;
          }
        ).catch(
          response => {
            alert('Request failed!');
          },
        );
      },
      handleAdd() {
        this.isEdit = false;
        this.selected = {
          name: '',
          phone: '',
          email: ''
        };
        this.dialog = true;
      },
      handleClick(data) {
        this.id = data.userId;
        this.isEdit = true;
        this.selected = {
          name: data.userName,
          phone: data.phone,
          email: data.email
        };
        this.dialog = true;
      },
      handleDelete(data) {
        let _this = this;
        this.$confirm('Are you sure to delete?', 'Confirm', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          const url = '/icbc/hzyjs/user/delete/' + data.userId;
          this.$axios.get(url).then(
              response => {
                // 刷新列表
                this.handleQuery();
                // // 根据id删除对应记录
                // let id = data.userId;
                // for (let i = 0; i < this.tableData.length; i++) {
                //   if (id == this.tableData[i].userId) {
                //     this.tableData.splice(i, 1);
                //     break;
                //   }
                // }
                this.$message({
                  type: 'success',
                  message: 'Delete success!'
                });
              }
            ).catch(
              response => {
                alert('Request failed!');
              },
            );
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Cancel success'
          });
        });
      },
      onSubmit() {
        let url = this.isEdit ? '/icbc/hzyjs/user/update' : '/icbc/hzyjs/user/insert' ;
        this.$axios({
          url: url,
          method: "post",
          data: {
            userId: this.id,
            userName: this.selected.name,
            phone: this.selected.phone,
            email: this.selected.email
          }
        }).then(
          response => {
            this.$message({
              type: 'success',
              message: 'Success!'
            });
            this.handleQuery();
            this.dialog = false;
          }
        ).catch(
          response => {
            alert('Request failed!');
          },
        );
      }
    }
  };
</script>

<style>
  
</style>
