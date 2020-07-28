<template>
  <v-content>
    <v-container>
      <v-row style="padding: 12px;">
        <el-button type="primary" @click="handleAdd()">Add material</el-button>
      </v-row>
      <v-row>
        <v-col cols="12">
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column fixed prop="material_id" label="MATERIAL_ID" width="150">
            </el-table-column>
            <el-table-column prop="material_name" label="MATERIAL_NAME" width="200">
            </el-table-column>
            <el-table-column prop="material_type" label="MATERIAL_TYPE" width="200">
            </el-table-column>
            <el-table-column prop="material_number" label="MATERIAL_NUMBER" width="200">
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
            <el-form-item label="material_name">
              <el-input v-model="selected.material_name"></el-input>
            </el-form-item>
            <el-form-item label="material_type">
              <el-input v-model="selected.material_type"></el-input>
            </el-form-item>
            <el-form-item label="material_number">
              <el-input v-model="selected.material_number"></el-input>
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
  
  const materialData = require('@/mock_data/material.json');

  export default {
    name: 'Material', // 物资管理
  
    components: {
    },
  
    data: () => ({
      cars: [],
      dialog: false,
      selected: {
          material_name: '',
          material_type: '',
          material_number: '',
          create_time: ''
        },
      tableData: materialData 
    }),
    computed: {},
  
    created() {},
  
    mounted() {
    },
  
    methods: {
  
      handleAdd() {
        this.selected = {
          material_name: '',
          material_type: '',
          material_number: '',
          create_time: ''
        };
        this.dialog = true;
      },
      handleClick(data) {
        this.selected = {
          material_name: data.material_name,
          material_type: data.material_type,
          material_number: data.material_number,
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
