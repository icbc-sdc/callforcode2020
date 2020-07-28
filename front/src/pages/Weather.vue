<template>
  <v-content>
    <v-container>
      <v-row>
        <v-col cols="12">
          <v-card tile style="height: 80vh;" flat>
            <LeafletMap :weather="weather" ref="map" />
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

<script>
import LeafletMap from '@/components/Map.vue';
import API from '@/API';

export default {
  name: 'Weather',

  components: {
    LeafletMap
  },

  data: () => ({
    weather: []
  }),
  computed: {},

  created() {},

  mounted() {
    // TODO 获取数据
    this.weather = API.getWeather();
  },

  methods: {}
};
</script>

<style scoped>
.ps {
  height: 70vh;
}
</style>
