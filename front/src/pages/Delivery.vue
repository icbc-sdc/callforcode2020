<template>
  <v-content>
    <v-container>
      <v-row>
        <v-col cols="12">
          <div id="tomtommap" style="height: 80vh;"></div>
        </v-col>
      </v-row>
    </v-container>
  </v-content>
</template>

<script>
/* eslint-disable */
// import LeafletMap from '@/components/Map.vue';
// import API from '@/API';
import tt from '@tomtom-international/web-sdk-maps';
import ttServices from '@tomtom-international/web-sdk-services';

export default {
  name: 'Delivery',

  components: {
    // LeafletMap
  },

  data: () => ({
    cars: [],
    dialog: false,
    selected: {}
  }),
  computed: {},

  created() {},

  mounted() {
    // TODO 获取数据
    // this.cars = API.getCars();
    // console.log(cars.length);
    const fff = function() {
      const nav = new tt.NavigationControl({});
      this.addControl(nav, 'bottom-right');
      this.addControl(new tt.FullscreenControl());
      this.flyTo({
        center: ['-8.501865', '40.110215'],
        // center: ['37', '-87'],
        duration: 500
      });
    };
    const tomtomMap = tt.map({
      key: '8OUZge16IwGa40HT7R6UX5AG71idTtA7',
      container: 'tomtommap',
      style: 'tomtom://vector/1/basic-night',
      zoom: 9,
        center: ['-8.501865', '40.110215'],
      // center: [37, -87],
      initialized: fff.bind(this)
    });
    tomtomMap.addControl(new tt.FullscreenControl());

    tomtomMap.on('load', function() {
      const popup = new tt.Popup({
        offset: 30
      }).setHTML('<h3>End of delivery!</h3>');

      const marker = document.createElement('div');
      const innerElement = document.createElement('div');

      marker.className = 'xxx-route-marker';
      innerElement.className = `icon tt-icon -white -start xxxxxx`;
      marker.appendChild(innerElement);
      const mk = new tt.Marker({ element: marker })
        .setPopup(popup)
        .setLngLat(['-8.501865', '40.110215'])
        // .setLngLat(['37', '-87'])
        .addTo(tomtomMap);
      // tomtomMap.on('click', () => {
      //   debugger;
      // });
      mk.on('load', function() {
        mk.on('click', () => {
          debugger;
        });
      });

      // const bounds = new tt.LngLatBounds();

      // bounds.extend(tt.LngLat.convert(['-8.501865', '40.110215']));
      // bounds.extend(tt.LngLat.convert(['-8.423538', '40.208555']));
      // if (!bounds.isEmpty()) {
      //   tomtomMap.fitBounds(bounds, { duration: 0, padding: 100 });
      // }
      calculateRoute();
    });

    const findFirstBuildingLayerId = function() {
      const layers1 = tomtomMap.getStyle().layers;
      let rst;
      Object.keys(layers1).forEach(index => {
        if (layers1[index].type === 'fill-extrusion') {
          rst = layers1[index].id;
        }
      });
      return rst;
    };
    const calculateRoute = function() {
      if (tomtomMap.getLayer('route')) {
        tomtomMap.removeLayer('route');
        tomtomMap.removeSource('route');
      }
      ttServices.services
        .calculateRoute({
          key: '8OUZge16IwGa40HT7R6UX5AG71idTtA7',
          traffic: false,
          locations: '-8.501865,40.110215:-8.423538,40.208555'
          // locations: '37, -87:36.92, -87.2'
        })
        .go()
        .then(response => {
          debugger;
          const geojson = response.toGeoJson();
          tomtomMap.addLayer(
            {
              id: 'route',
              type: 'line',
              source: {
                type: 'geojson',
                data: geojson
              },
              paint: {
                'line-color': '#2faaff',
                'line-width': 8
              }
            },
            findFirstBuildingLayerId()
          );
        })
        .catch(() => {});
    };
  },

  methods: {
    // viewCarData(location) {
    //   this.selected = location;
    //   this.dialog = true;
    // }
  }
};
</script>

<style>
.xxx-route-marker {
  align-items: center;
  background-color: #4a90e2;
  border: solid 3px #2faaff;
  border-radius: 50%;
  display: flex;
  height: 32px;
  justify-content: center;
  transition: width 0.1s, height 0.1s;
  width: 32px;
}
.xxxxxx {
  background-size: cover;
  height: 16px;
  width: 16px;

  /* background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3E%3Cg fill='none' fill-rule='evenodd'%3E%3Cpath fill-rule='nonzero' stroke='white' d='M-19.5-19.5h55v55h-55z'/%3E%3Cpath fill='white' d='M15.493.015a.4.4 0 0 1 .492.493L11.728 15.66a.4.4 0 0 1-.757.036l-2.914-7.55a.4.4 0 0 0-.23-.229L.257 5.015a.4.4 0 0 1 .035-.758l15.2-4.242z'/%3E%3C/g%3E%3C/svg%3E"); */
  background-image: url('data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBzdGFuZGFsb25lPSJubyI/PjwhRE9DVFlQRSBzdmcgUFVCTElDICItLy9XM0MvL0RURCBTVkcgMS4xLy9FTiIgImh0dHA6Ly93d3cudzMub3JnL0dyYXBoaWNzL1NWRy8xLjEvRFREL3N2ZzExLmR0ZCI+PHN2ZyB0PSIxNTg3NTQ1MDg3NDk2IiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHAtaWQ9IjE1NDAiIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB3aWR0aD0iMTI4IiBoZWlnaHQ9IjEyOCI+PGRlZnM+PHN0eWxlIHR5cGU9InRleHQvY3NzIj48L3N0eWxlPjwvZGVmcz48cGF0aCBkPSJNNDEyLjU3NCA1OTkuMDk0SDE1Ny40NTJWNDI0Ljk1MmgyNTYuMjYzbC0xNDQuNDgtMTQ0LjQ4Yy0xNy44NS0xNy44NS00Ni43OS0xNy44NS02NC42NCAwTDIzLjY3MiA0NjMuMDA5Yy0yOC44ODIgMjguODgyLTAuNjM3IDM2LjEwOC0wLjg3NSA0Ny40ODItMC4yNTIgMTIuMDE2LTI2LjAxMiAyNC4xMzEtMC43MzggNDkuNDA2bDE4Mi41MzcgMTgyLjUzN2MxNy44NDkgMTcuODUgNDYuNzg5IDE3Ljg1IDY0LjYzOSAwbDE0My4zMzktMTQzLjM0ek01OTkuMjU5IDYxMi43NzZ2MjUzLjk4M0g0MjUuMTE2VjYxMS42MzZsLTE0NC40NzkgMTQ0LjQ4Yy0xNy44NSAxNy44NDktMTcuODUgNDYuNzg5IDAgNjQuNjM5bDE4Mi41MzcgMTgyLjUzNmMyNS4yNzQgMjUuMjc0IDM3LjM5LTAuNDg2IDQ5LjQwNS0wLjczOCAxMS4zNzUtMC4yMzggMTguNjAxIDI4LjAwNyA0Ny40ODMtMC44NzVsMTgyLjUzNi0xODAuOTI0YzE3Ljg1LTE3Ljg1IDE3Ljg1LTQ2Ljc5IDAtNjQuNjM5TDU5OS4yNTkgNjEyLjc3NnpNMTAwMS41NzggNTEzLjU1NWMwLjI1MS0xMi4wMTYgMjYuMDEyLTI0LjEzMSAwLjczNy00OS40MDZMODE5Ljc3OSAyODEuNjEzYy0xNy44NS0xNy44NS00Ni43OS0xNy44NS02NC42MzkgMEw2MTEuODAxIDQyNC45NTJoMjU1LjEyMnYxNzQuMTQzSDYxMC42NjFMNzU1LjE0IDc0My41NzNjMTcuODQ5IDE3Ljg1IDQ2Ljc4OSAxNy44NSA2NC42MzkgMGwxODAuOTI0LTE4Mi41MzZjMjguODgxLTI4Ljg4MSAwLjYzNy0zNi4xMDcgMC44NzUtNDcuNDgyek00MjUuMTE2IDQxMS4yN1YxNTcuMjg3aDE3NC4xNDNWNDEyLjQxbDE0NC40NzktMTQ0LjQ3OWMxNy44NDktMTcuODUgMTcuODQ5LTQ2Ljc5IDAtNjQuNjM5TDU2MS4yMDIgMjAuNzU1Yy0yNS4yNzUtMjUuMjc0LTM3LjM5IDAuNDg2LTQ5LjQwNiAwLjczOC0xMS4zNzUgMC4yMzgtMTguNjAxLTI4LjAwNy00Ny40ODIgMC44NzVMMjgxLjc3NyAyMDMuMjkyYy0xNy44NSAxNy44NS0xNy44NSA0Ni43OSAwIDY0LjYzOUw0MjUuMTE2IDQxMS4yN3oiIGZpbGw9IiNENzRBNDkiIHAtaWQ9IjE1NDEiPjwvcGF0aD48L3N2Zz4=');
  background-position: 50%;
  background-repeat: no-repeat;
  display: inline-block;
}
.mapboxgl-marker {
  cursor: pointer;
}
</style>
