<template>
  <v-dialog :fullscreen="$vuetify.breakpoint.smAndDown" max-width="400" v-model="dialog">
    <v-card>
      <v-card-title>
        <span class="title font-weight-light">
          <div class="headline">Search place</div>
        </span>
        <v-spacer></v-spacer>
        <v-btn icon @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
      </v-card-title>
      <v-card-text>
        <v-text-field
          @keyup="change"
          v-model="search"
          label="Type here..."
          append-icon="mdi-magnify"
          clearable
        ></v-text-field>
        <v-list two-line v-if="result.length">
          <v-list-item
            @click="$emit('PLACE_SELECTED', item)"
            v-for="(item, idx) in result"
            :key="idx"
          >
            <v-list-item-content>
              <v-list-item-title>
                {{ item['Combined_Key'] }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <div v-else>
          {{ resultText }}
        </div>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
import Vue2Filters from 'vue2-filters';

export default {
  props: ['data'],
  data: () => ({
    dialog: false,
    search: null,
    result: [],
    resultText: 'Result will appear here.'
  }),
  methods: {
    change($e) {
      const { value } = $e.target;

      if (!value) return;

      this.result = this.filterBy(this.data, $e.target.value, 'Combined_Key');

      if (!this.result.length) {
        this.resultText = 'No Area found.';
      }
    }
  },
  watch: {
    dialog(val) {
      if (!val) {
        this.result = [];
        this.resultText = 'Result will appear here.';
        this.search = null;
      }
    },
    search(val) {
      if (!val) {
        this.result = [];
        this.resultText = 'Result will appear here.';
      }
    }
  },
  mixins: [Vue2Filters.mixin]
};
</script>
