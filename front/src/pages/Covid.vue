<template>
  <v-content>
    <v-container>
      <v-row>
        <v-col cols="12">
          <TotalCases
            :infected="cases.total_confirmed"
            :recovered="cases.total_recovered"
            :deaths="cases.total_death"
          />
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="9">
          <v-card tile style="height: 80vh;" flat>
            <LeafletMap :data="cases.data" ref="map" @MARKER_CLICKED="viewData" />
          </v-card>
        </v-col>
        <v-col cols="3">
          <v-card tile style="height: 80vh;overflow:auto;" flat>
            <v-list three-line>
              <div v-if="loading">
                <v-list-item link v-for="i in 20" :key="i">
                  <v-list-item-content>
                    <v-list-item-title>
                      <v-skeleton-loader type="text"></v-skeleton-loader>
                    </v-list-item-title>
                    <v-list-item-subtitle>
                      <v-skeleton-loader width="140" type="text"></v-skeleton-loader>
                    </v-list-item-subtitle>
                    <v-list-item-subtitle>
                      <v-skeleton-loader width="140" type="text"></v-skeleton-loader>
                    </v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </div>
              <v-list-item @click="view(l)" v-else v-for="(l, idx) in locations" :key="idx" link>
                <v-list-item-content>
                  <v-list-item-title :title="l['Combined_Key']">
                    {{ l['Combined_Key'] }}
                  </v-list-item-title>
                  <v-list-item-subtitle>
                    Confirmed:
                    <span class="font-weight-bold">
                      <span class="red--text text--darken-2">{{ l.confirmed }}</span>
                    </span>
                  </v-list-item-subtitle>
                  <v-list-item-subtitle>
                    Deaths:
                    <span>{{ l.death }}</span>
                  </v-list-item-subtitle>
                  <v-list-item-subtitle>
                    Recovered:
                    <span>{{ l.recovered }}</span>
                  </v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="12">
          <Timeline />
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

<script>
import LeafletMap from '@/components/Map.vue';
import Timeline from '@/components/Timeline.vue';
import TotalCases from '@/components/TotalCases.vue';

export default {
  name: 'Covid',

  components: {
    LeafletMap,
    Timeline,
    TotalCases
  },

  data: () => ({
    loading: false
  }),
  props: ['cases'],
  computed: {
    locations() {
      const data = [];
      this.cases.data.forEach(item => {
        const confirmed = item.Confirmed;
        const recovered = item.Recovered;
        const death = item.Deaths;
        const obj = {
          ...item,
          confirmed,
          recovered,
          death
        };
        delete obj.Admin2;
        delete obj.dates;
        data.push(obj);
      });
      return data.sort((a, b) => b.confirmed - a.confirmed);
    }
  },

  created() {},

  mounted() {},

  methods: {
    view(location) {
      if (this.$vuetify.breakpoint.smAndDown) {
        this.drawer = false;
      }

      window.scrollTo({ top: 150, behavior: 'smooth' });
      const { Lat, Long_ } = location;
      this.$refs.map.flyTo(Lat, Long_);
    },
    viewData(location) {
      this.$emit('viewDetails', location);
    }
  }
};
</script>

<style scoped>
.ps {
  height: 70vh;
}
</style>
