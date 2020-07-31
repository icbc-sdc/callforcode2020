<template>
  <div id="app">
    <v-app>
      <v-app-bar app clipped-left>
        <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
        <v-toolbar-title>
          <span v-show="!$vuetify.breakpoint.smAndUp">CFC</span>
          <span v-show="$vuetify.breakpoint.smAndUp">WARM WIND</span>
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-tooltip left :dark="isDarkTheme">
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="$refs.searchDialog.dialog = true">
              <v-icon>mdi-map-search</v-icon>
            </v-btn>
          </template>
          <span>Search</span>
        </v-tooltip>
        <v-tooltip left :dark="isDarkTheme">
          <template v-slot:activator="{ on }">
            <v-btn icon v-on="on" @click="TOGGLE_THEME">
              <v-icon>mdi-theme-light-dark</v-icon>
            </v-btn>
          </template>
          <span>Switch theme</span>
        </v-tooltip>
      </v-app-bar>
      <v-navigation-drawer v-model="drawer" app clipped dark>
        <v-list>
          <v-divider></v-divider>
          <v-list-item @click="nav('')" key="covid" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-view-dashboard</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Cases</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <!-- <v-list-item @click="nav('delivery')" key="delivery" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-google-fit</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Relief</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider> -->
          <v-list-item @click="nav('delivery')" key="delivery" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-map-marker-distance</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Logistical</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="nav('distribution')" key="distribution" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-medical-bag</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Distribution</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="nav('weather')" key="weather" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-weather-partly-snowy-rainy</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Weather</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="nav('user')" key="user" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-account-edit</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>User</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="nav('role')" key="role" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-account-group</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Role</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="nav('site')" key="site" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-domain</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Site</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="nav('material')" key="material" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-drama-masks</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Material</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="nav('transfer')" key="transfer" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-truck-delivery</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Transfer</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="nav('car')" key="car" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-car</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Car</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <v-list-item @click="nav('city')" key="city" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-city</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>City</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-divider></v-divider>
          <!-- <v-list-item @click="nav('delivery')" key="delivery" link>
            <v-list-item-icon>
              <v-icon style="margin-right:20px;">mdi-umbrella</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>Insurance</v-list-item-title>
            </v-list-item-content>
          </v-list-item> -->
          <!-- <v-divider></v-divider>
          <v-divider></v-divider> -->
        </v-list>
      </v-navigation-drawer>
      <SearchDialog ref="searchDialog" :data="cases.data" @PLACE_SELECTED="viewDetails" />
      <v-dialog width="500" v-model="dialog">
        <v-card>
          <v-card-title>
            <span class="headline">Confirmed Cases</span>
          </v-card-title>
          <v-card-text>
            <div>
              <p>
                Area: <span style="font-weight:bold;">{{ selected['Combined_Key'] }}</span>
              </p>
              <p>
                Confirmed:
                <ICountUp class="red--text text--darken-2" :endVal="selected.confirmed" />
              </p>
              <p>
                Deaths:
                <ICountUp :endVal="selected.death" />
              </p>
              <p>
                Recovered:
                <ICountUp :endVal="selected.recovered" />
              </p>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn text @click="dialog = false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <router-view :cases="cases" @viewDetails="viewDetails"></router-view>
    </v-app>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import ICountUp from 'vue-countup-v2';
import SearchDialog from '@/components/SearchDialog.vue';
import API from './API';

export default {
  name: 'Layout',
  components: {
    ICountUp,
    SearchDialog
  },
  data: () => ({
    drawer: null,
    cases: {
      total_confirmed: 0,
      total_recovered: 0,
      total_death: 0,
      data: []
    },
    selected: {},
    dialog: false
  }),
  computed: {
    ...mapState(['isDarkTheme'])
  },
  created() {
    this.$vuetify.theme.dark = this.isDarkTheme;
    const datas = API.getCases();
    this.cases = {
      total_confirmed: 17029155,
      total_recovered: 9948163,
      total_death: 667011,
      		

      data: datas
    };
  },
  watch: {
    isDarkTheme(val) {
      this.$vuetify.theme.dark = val;
    }
  },
  methods: {
    ...mapMutations(['TOGGLE_THEME']),
    nav(type) {
      this.$router.push(`/${type}`);
    },
    viewDetails(location) {
      this.dialog = true;
      this.selected = {
        ...location,
        confirmed: location.Confirmed,
        death: location.Deaths,
        recovered: location.Recovered
      };
    }
  }
};
</script>
