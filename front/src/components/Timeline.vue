<template>
  <v-card tile flat>
    <v-card-title>
      <span class="title font-weight-light">Timeline</span>
    </v-card-title>
    <v-data-table :headers="headers" :items="items" :items-per-page="10">
      <template v-slot:item.source="{ item }">
        <a target="_BLANK" style="text-decoration: none;" :href="item.source">
          Source
        </a>
      </template>
      <template v-slot:item.datetime="{ item }">
        {{ item.datetime | toDate }}
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
import moment from 'moment';
import API from '../API';

export default {
  data: () => ({
    items: [],
    tab: null,
    loading: false,
    headers: [
      { text: 'Date', value: 'datetime' },
      { text: 'Description', value: 'description', width: '50%' },
      { text: '', value: 'source', sortable: false }
    ]
  }),
  mounted() {
    this.loading = true;
    const data = API.getTimeline();
    data.forEach(item => {
      const times = item.time.map(i => ({
        datetime: +moment(`${item.date} ${i.time}`, 'D MMMM HH:mm').format('x'),
        source: i.source,
        description: i.description
      }));
      this.items = [...this.items, ...times];
    });
    this.loading = false;
  },
  filters: {
    toDate(val) {
      return moment(val).format('MMMM D HH:mm');
    }
  }
};
</script>

<style scoped>
.v-tab--active {
  color: #fff;
}
</style>
