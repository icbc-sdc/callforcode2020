<template>
  <v-content>
    <v-container>
      <v-row style="padding: 12px;">
        <el-button type="primary" @click="handleAdd()">Add role</el-button>
      </v-row>
      <v-row>
        <v-col cols="12">
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column fixed prop="role_id" label="ROLE_ID" width="150">
            </el-table-column>
            <el-table-column prop="role_name" label="ROLE_NAME" width="200">
            </el-table-column>
            <el-table-column prop="statue" label="STATUE" width="200">
            </el-table-column>
            <el-table-column prop="create_time" label="CREATE_TIME" min-width="200">
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
            <el-form-item label="role_name">
              <el-input v-model="selected.role_name"></el-input>
            </el-form-item>
            <el-form-item label="statue">
              <el-input v-model="selected.statue"></el-input>
            </el-form-item>
            <el-form-item label="create_time">
              <el-input v-model="selected.create_time"></el-input>
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
  
  const roleData = require('@/mock_data/role.json');

  export default {
    name: 'Role', // 角色管理
  
    components: {
      // LeafletMap
    },
  
    data: () => ({
      cars: [],
      dialog: false,
      selected: {
          role_name: '',
          statue: '',
          create_time: ''
        },
      tableData: roleData 
    }),
    computed: {},
  
    created() {},
  
    mounted() {
    },
  
    methods: {
  
      handleAdd() {
        this.selected = {
          role_name: '',
          statue: '',
          create_time: ''
        };
        this.dialog = true;
      },
      handleClick(data) {
        this.selected = {
          role_name: data.role_name,
          statue: data.statue,
          create_time: data.create_time
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
