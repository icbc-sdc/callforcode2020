<template>
  <v-content>
    <v-container>
      <v-row style="padding: 12px;">
        <el-button type="primary" @click="handleAdd()">Add city</el-button>
      </v-row>
      <v-row>
        <v-col cols="12">
          <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="cityId" label="CITY_ID" width="150">
            </el-table-column>
            <el-table-column prop="cityName" label="CITY_NAME" width="150">
            </el-table-column>
            <el-table-column prop="cityLatitude" label="CITY_LATITUDE" width="150">
            </el-table-column>
            <el-table-column prop="cityLongtitude" label="CITY_LONGTITUDE" width="150">
            </el-table-column>
            <el-table-column prop="cityStatus" label="CITY_STATUS" width="150">
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
            <el-form-item label="cityName">
              <el-input v-model="selected.cityName"></el-input>
            </el-form-item>
            <el-form-item label="cityLatitude">
              <el-input v-model="selected.cityLatitude"></el-input>
            </el-form-item>
            <el-form-item label="cityLongtitude">
              <el-input v-model="selected.cityLongtitude"></el-input>
            </el-form-item>
            <el-form-item label="cityStatus">
              <el-input v-model="selected.cityStatus"></el-input>
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
    name: 'City', // 城市管理
  
    components: {
      // LeafletMap
    },
  
    data: () => ({
      id: '',
      isEdit: false,
      dialog: false,
      selected: {
        cityName: '',
        cityLatitude: '',
        cityLongtitude: '',
        cityStatus: ''
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
        const url = '/icbc/hzyjs/city/query';
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
          cityName: '',
          cityLatitude: '',
          cityLongtitude: '',
          cityStatus: ''
        };
        this.dialog = true;
      },
      handleClick(data) {
        this.id = data.cityId;
        this.isEdit = true;
        this.selected = {
          cityName: data.cityName,
          cityLatitude: data.cityLatitude,
          cityLongtitude: data.cityLongtitude,
          cityStatus: data.cityStatus
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
          const url = '/icbc/hzyjs/city/delete/' + data.cityId;
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
        let url = this.isEdit ? '/icbc/hzyjs/site/update' : '/icbc/hzyjs/site/insert' ;
        this.$axios({
          url: url,
          method: "post",
          data: {
            cityId: this.cityId,
            cityName: this.selected.cityName,
            cityLatitude: this.selected.cityLatitude,
            cityLongtitude: this.selected.cityLongtitude,
            cityStatus: this.selected.cityStatus
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
