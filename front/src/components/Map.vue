<template>
  <l-map
    ref="map"
    style="z-index: 0; height: 100%; width: 100%"
    :zoom="zoom"
    :center="center"
    @update:zoom="zoomUpdated"
    @update:center="centerUpdated"
    @update:bounds="boundsUpdated"
  >
    <l-tile-layer :url="tileLayerUrl"></l-tile-layer>
    <!-- <l-control-attribution position="topright" prefix="cases"></l-control-attribution> -->
    <l-circle-marker
      @click="$emit('MARKER_CLICKED', l)"
      v-for="(l, idx) in locations"
      :key="'circle' + idx"
      :lat-lng="[+l.Lat, +l.Long_]"
      color="red"
      fillColor="#f00"
      :fillOpacity="0.35"
      :stroke="false"
      :radius="l.radius"
    />
    <l-marker
      @click="$emit('CAR_CLICKED', l)"
      v-for="(l, idx) in cars"
      :key="'car' + idx"
      :lat-lng="[+l.Lat, +l.Long_]"
    >
      <l-icon :icon-anchor="[15, 15]">
        <img :src="carLogo" />
      </l-icon>
    </l-marker>

    <!-- <l-rectangle :bounds="rectangle.bounds" :l-style="rectangle.style"></l-rectangle> -->
    <l-image-overlay
      :url="rainImg"
      :opacity="0.8"
      v-for="(l, idx) in rainData"
      :key="'rain' + idx"
      :bounds="l.bounds"
    ></l-image-overlay>
    <l-image-overlay
      :opacity="0.5"
      :url="stormImg"
      v-for="(l, idx) in stormData"
      :key="'storm' + idx"
      :bounds="l.bounds"
    ></l-image-overlay>
    <l-image-overlay
      :opacity="0.5"
      :url="snowImg"
      v-for="(l, idx) in snowData"
      :key="'snow' + idx"
      :bounds="l.bounds"
    ></l-image-overlay>
  </l-map>
</template>

<script>
import carLogo from '@/assets/car.png';
import rainImg from '@/assets/rain.jpg';
import stormImg from '@/assets/storm.jpg';
import snowImg from '@/assets/snow.jpg';

import {
  LMarker,
  LMap,
  LTileLayer,
  LCircleMarker,
  LIcon,
  // LRectangle,
  LImageOverlay
} from 'vue2-leaflet';
import { mapState } from 'vuex';

export default {
  name: 'Map',
  props: ['data', 'cars', 'weather'],
  data: () => ({
    carLogo: `${carLogo}`,
    rainImg,
    stormImg,
    snowImg,
    url: 'https://tiles.stadiamaps.com/tiles/alidade_smooth_dark/{z}/{x}/{y}{r}.png',
    zoom: 4,
    center: [38, -95]
  }),
  computed: {
    ...mapState(['isDarkTheme']),
    rainData() {
      if (!this.weather) {
        return [];
      }
      return this.weather.filter(i => i.type === 'rain');
    },
    stormData() {
      if (!this.weather) {
        return [];
      }
      return this.weather.filter(i => i.type === 'storm');
    },
    snowData() {
      if (!this.weather) {
        return [];
      }
      return this.weather.filter(i => i.type === 'snow');
    },
    locations() {
      if (!this.data) {
        return [];
      }
      const withConfirmedData = this.data.filter(i => i.Confirmed); // && i.Country_Region !== 'US');
      return withConfirmedData.map(item => ({
        ...item,
        radius: this.scale(item.Confirmed)
      }));
    },
    tileLayerUrl() {
      const darkTheme = 'https://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}{r}.png';
      const lightTheme = 'https://{s}.basemaps.cartocdn.com/rastertiles/voyager/{z}/{x}/{y}{r}.png';
      return this.isDarkTheme ? darkTheme : lightTheme;
    }
  },
  mounted() {
    // this.getUserLocation();
  },
  methods: {
    zoomUpdated(zoom) {
      this.zoom = zoom;
    },
    centerUpdated(center) {
      this.center = center;
    },
    boundsUpdated(bounds) {
      this.bounds = bounds;
    },
    flyTo(lat, lon) {
      this.$refs.map.mapObject.flyTo([lat, lon], 4);
    },
    scale(d) {
      const min = 1;
      const factor = 5;
      const zoomFactor = this.zoom / 50; // adjust divisor for best optics
      // const zoomFactor = this.zoom >= 5 ? 1 : this.zoom / 10; // adjust divisor for best optics
      return Math.floor(Math.log(d) * factor * zoomFactor) + min;
    },
    getUserLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(({ coords }) => {
          this.flyTo(coords.latitude, coords.longitude);
        });
      }
    }
  },
  components: {
    LMap,
    LTileLayer,
    LCircleMarker,
    LMarker,
    LIcon,
    LImageOverlay
  }
};
</script>
