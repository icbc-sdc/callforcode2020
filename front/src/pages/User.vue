<template>
  <v-content>
    <v-container>
      <v-row style="padding: 12px;">
        <el-button type="primary" @click="handleAdd()">Add user</el-button>
      </v-row>
      <v-row>
        <v-col cols="12">
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column fixed prop="user_id" label="USER_ID" width="150">
            </el-table-column>
            <el-table-column prop="user_name" label="USER_NAME" width="150">
            </el-table-column>
            <el-table-column prop="role_name" label="ROLE_NAME" width="150">
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
          <el-form ref="form" :model="selected" label-width="80px">
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
              <el-button @click="onSubmit">cancel</el-button>
            </el-form-item>
          </el-form>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-container>
  </v-content>
</template>

<script>
  
  const userData = require('@/mock_data/user.json');

  export default {
    name: 'User', // 用户管理
  
    components: {
      // LeafletMap
    },
  
    data: () => ({
      cars: [],
      dialog: false,
      selected: {
          name: '',
          phone: '',
          email: ''
        },
      tableData: userData 
    }),
    computed: {},
  
    created() {},
  
    mounted() {
    },
  
    methods: {
  
      handleAdd() {
        this.selected = {
          name: '',
          phone: '',
          email: ''
        };
        this.dialog = true;
      },
      handleClick(data) {
        this.selected = {
          name: data.user_name,
          phone: data.phone,
          email: data.email
        };
        this.dialog = true;
      },
      handleDelete(data) {
        this.$confirm('Are you sure to delete?', 'Confirm', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: 'Delete success!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: 'Cancel success'
          });          
        });
      },
      onSubmit() {
        this.dialog = false;
      }
    }
  };
</script>

<style>
</style>
