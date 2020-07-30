<template>
  <v-content>
    <v-container>
      <v-row style="padding: 12px;">
        <el-button type="primary" @click="handleAdd()">Add transfer</el-button>
      </v-row>
      <v-row>
        <v-col cols="12">
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="id" label="ID" width="150">
            </el-table-column>
            <el-table-column prop="materialId" label="MATERIAL_ID" width="200">
            </el-table-column>
            <el-table-column prop="materialNumber" label="MATERIAL_NUMBER" width="200">
            </el-table-column>
            <el-table-column prop="startSiteId" label="START_SITE_ID" width="200">
            </el-table-column>
            <el-table-column prop="endSiteId" label="END_SITE_ID" width="200">
            </el-table-column>
            <el-table-column prop="createTime" label="CREATE_TIME" min-width="200">
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
            <el-form-item label="materialId">
              <el-input v-model="selected.materialId"></el-input>
            </el-form-item>
            <el-form-item label="materialNumber">
              <el-input v-model="selected.materialNumber"></el-input>
            </el-form-item>
            <el-form-item label="startSiteId">
              <el-input v-model="selected.startSiteId"></el-input>
            </el-form-item>
            <el-form-item label="endSiteId">
              <el-input v-model="selected.endSiteId"></el-input>
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
    name: 'Transfer', // 物资转移
  
    components: {},
  
    data: () => ({
      id: '',
      isEdit: false,
      dialog: false,
      selected: {
        materialId: '',
        materialNumber: '',
        startSiteId: '',
        endSiteId: ''
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
        const url = '/icbc/hzyjs/transfer/query';
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
          materialId: '',
          materialNumber: '',
          startSiteId: '',
          endSiteId: ''
        };
        this.dialog = true;
      },
      handleClick(data) {
        this.id = data.id;
        this.isEdit = true;
        this.selected = {
          materialId: data.materialId,
          materialNumber: data.materialNumber,
          startSiteId: data.startSiteId,
          endSiteId: data.endSiteId
        };
        this.dialog = true;
      },
      handleDelete(data) {
        this.$confirm('Are you sure to delete?', 'Confirm', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          const url = '/icbc/hzyjs/transfer/delete/' + data.id;
          this.$axios.get(url).then(
            response => {
              // 刷新列表
              this.handleQuery();
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
        let url = this.isEdit ? '/icbc/hzyjs/transfer/update' : '/icbc/hzyjs/transfer/insert';
        this.$axios({
          url: url,
          method: "post",
          data: {
            id: this.id,
            materialId: this.selected.materialId,
            materialNumber: this.selected.materialNumber,
            startSiteId: this.selected.startSiteId,
            endSiteId: this.selected.endSiteId
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
