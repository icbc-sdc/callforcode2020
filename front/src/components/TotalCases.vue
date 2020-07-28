<template>
  <v-row>
    <v-col cols="4" class="d-flex flex-column justify-center align-center">
      <span :class="[count]"><ICountUp :endVal="infected"/></span>
      <span :class="[title]">Confirmed</span>
    </v-col>
    <v-col cols="4" class="d-flex flex-column justify-center align-center">
      <span :class="[count]"><ICountUp :endVal="recovered"/></span>
      <span :class="[title]">Recovered</span>
    </v-col>
    <v-col cols="4" class="d-flex flex-column justify-center align-center">
      <span :class="[count]"><ICountUp :endVal="deaths"/></span>
      <span :class="[title]">Deaths</span>
    </v-col>
  </v-row>
</template>

<script>
import ICountUp from 'vue-countup-v2';

export default {
  props: ['infected', 'recovered', 'deaths'],
  components: {
    ICountUp
  },
  computed: {
    count() {
      if (this.$vuetify.breakpoint.mdAndUp) {
        return 'display-2 font-weight-bold';
      }

      return 'display-1 font-weight-bold';
    },
    title() {
      if (this.$vuetify.breakpoint.mdAndUp) {
        return 'mt-4 title';
      }

      return 'mt-4 subtitle-1';
    }
  }
};
</script>
