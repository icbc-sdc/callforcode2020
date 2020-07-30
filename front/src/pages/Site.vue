<template>
  <v-content>
    <v-container>
      <v-row style="padding: 12px;">
        <el-button type="primary" @click="handleAdd()">Add site</el-button>
      </v-row>
      <v-row>
        <v-col cols="12">
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column fixed prop="siteId" label="SITE_ID" width="150">
            </el-table-column>
            <el-table-column prop="siteName" label="SITE_NAME" width="200">
            </el-table-column>
            <el-table-column prop="validFlag" label="VALID" width="200">
            </el-table-column>
            <el-table-column prop="siteLatitude" label="SITE_LATITUDE" width="150">
            </el-table-column>
            <el-table-column prop="siteLongtitude" label="SITE_LONGTITUDE" width="150">
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
            <el-form-item label="siteName">
              <el-input v-model="selected.siteName"></el-input>
            </el-form-item>
            <el-form-item label="validFlag">
              <el-input v-model="selected.validFlag"></el-input>
            </el-form-item>
            <el-form-item label="siteLatitude">
              <el-input v-model="selected.siteLatitude"></el-input>
            </el-form-item>
            <el-form-item label="siteLongtitude">
              <el-input v-model="selected.siteLongtitude"></el-input>
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
    name: 'Site', // 站点管理
  
    components: {},
  
    data: () => ({
      id: '',
      isEdit: false,
      dialog: false,
      selected: {
        siteName: '',
        validFlag: '',
        siteLatitude: '',
        siteLongtitude: ''
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
        const url = '/icbc/hzyjs/site/query';
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
          siteName: '',
          validFlag: '',
          siteLatitude: '',
          siteLongtitude: ''
        };
        this.dialog = true;
      },
      handleClick(data) {
        this.id = data.siteId;
        this.isEdit = true;
        this.selected = {
          siteName: data.siteName,
          validFlag: data.validFlag,
          siteLatitude: '',
          siteLongtitude: ''
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
          const url = '/icbc/hzyjs/site/delete/' + data.siteId;
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
        let url = this.isEdit ? '/icbc/hzyjs/site/update' : '/icbc/hzyjs/site/insert';
        this.$axios({
          url: url,
          method: "post",
          data: {
            siteId: this.id,
            siteName: this.selected.siteName,
            validFlag: this.selected.validFlag,
            siteLatitude: '',
            siteLongtitude: ''
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
