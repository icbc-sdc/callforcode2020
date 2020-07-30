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
  import tt from '@tomtom-international/web-sdk-maps';
  import ttServices from '@tomtom-international/web-sdk-services';
  const distribution = require('@/mock_data/distribution.json');
  
  export default {
    name: 'Distribution', // 物资分发点
  
    components: {},
    data: () => ({}),
    computed: {},
    created() {},
  
    mounted() {
      let _this = this;
      const fff = function() {
        const nav = new tt.NavigationControl({});
        this.addControl(nav, 'bottom-right');
        this.addControl(new tt.FullscreenControl());
        this.flyTo({
          center: ['-96.501865', '40.110215'],
          duration: 500
        });
      };
      const tomtomMap = tt.map({
        key: '8OUZge16IwGa40HT7R6UX5AG71idTtA7',
        container: 'tomtommap',
        style: 'tomtom://vector/1/basic-night',
        zoom: 3.5,
        center: ['-96.501865', '40.110215'],
        initialized: fff.bind(this)
      });
      tomtomMap.addControl(new tt.FullscreenControl());
  
      tomtomMap.on('load', function() {
  
        for (let i = 0; i < distribution.length; i++) {
          const marker = document.createElement('div');
          const innerElement = document.createElement('div');
  
          marker.className = 'xxx-route-marker';
          innerElement.className = `icon tt-icon -white -start xxxxxx`;
          marker.appendChild(innerElement);
          const mk = new tt.Marker({
              element: marker
            })
            .setLngLat(distribution[i])
            .addTo(tomtomMap);
  
        }
      });
  
    },
  
    methods: {}
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
  
  .yyyyyy {
    background-size: cover;
    height: 26px;
    width: 36px;
    /* background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3E%3Cg fill='none' fill-rule='evenodd'%3E%3Cpath fill-rule='nonzero' stroke='white' d='M-19.5-19.5h55v55h-55z'/%3E%3Cpath fill='white' d='M15.493.015a.4.4 0 0 1 .492.493L11.728 15.66a.4.4 0 0 1-.757.036l-2.914-7.55a.4.4 0 0 0-.23-.229L.257 5.015a.4.4 0 0 1 .035-.758l15.2-4.242z'/%3E%3C/g%3E%3C/svg%3E"); */
    background-image: url('data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBzdGFuZGFsb25lPSJubyI/PjwhRE9DVFlQRSBzdmcgUFVCTElDICItLy9XM0MvL0RURCBTVkcgMS4xLy9FTiIgImh0dHA6Ly93d3cudzMub3JnL0dyYXBoaWNzL1NWRy8xLjEvRFREL3N2ZzExLmR0ZCI+PHN2ZyB0PSIxNTk1OTA2ODAwOTYxIiBjbGFzcz0iaWNvbiIgdmlld0JveD0iMCAwIDE0MzIgMTAyNCIgdmVyc2lvbj0iMS4xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHAtaWQ9IjEyMjYiIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB3aWR0aD0iMTc5IiBoZWlnaHQ9IjEyOCI+PGRlZnM+PHN0eWxlIHR5cGU9InRleHQvY3NzIj48L3N0eWxlPjwvZGVmcz48cGF0aCBkPSJNMTM0My41MzYwOSA4NzUuOTM5ODloLTgyLjE0OTIxNmE0Ny43NjExNzIgNDcuNzYxMTcyIDAgMCAxIDAtOTUuNTIyMzQ0SDEzMzcuMzI3MTM3VjQ2Ny4xMDQyNmwtMTkxLjA0NDY4Ny0yNDUuOTcwMDM1aC0yODYuNTY3MDNhNDcuNzYxMTcyIDQ3Ljc2MTE3MiAwIDAgMSAwLTk1LjUyMjM0M2gzMDcuMTA0MzM0YTQ3Ljc2MTE3MiA0Ny43NjExNzIgMCAwIDEgMzcuMjUzNzE0IDE4LjE0OTI0NWwyMTcuNzkwOTQzIDI3Ny40OTI0MDhhNTAuMTQ5MjMgNTAuMTQ5MjMgMCAwIDEgMTAuMDI5ODQ3IDI5LjYxMTkyNnYzMzQuMzI4MjAzYTg5Ljc5MTAwMyA4OS43OTEwMDMgMCAwIDEtODguMzU4MTY4IDkwLjc0NjIyNnpNOTQ4LjA3MzU4OCA4NzUuOTM5ODloLTE2NS43MzEyNjZhNDcuNzYxMTcyIDQ3Ljc2MTE3MiAwIDEgMSAwLTk1LjUyMjM0NGgxNjUuNzMxMjY2YTQ3Ljc2MTE3MiA0Ny43NjExNzIgMCAwIDEgMCA5NS41MjIzNDR6IiBmaWxsPSIjZDgxZTA2IiBwLWlkPSIxMjI3Ij48L3BhdGg+PHBhdGggZD0iTTEzODUuMDg4MzA5IDU3My4xMzQwNjFoLTMxOS4wNDQ2MjdhODkuMzEzMzkxIDg5LjMxMzM5MSAwIDAgMS04OS4zMTMzOTEtODkuMzEzMzkxVjMzNC4zMjgyMDJhODkuMzEzMzkxIDg5LjMxMzM5MSAwIDAgMSA4OS4zMTMzOTEtOTAuNzQ2MjI2aDE5NS4zNDMxOTJhNDcuNzYxMTcyIDQ3Ljc2MTE3MiAwIDAgMSAwIDk1LjUyMjM0M2gtMTkxLjA0NDY4N1Y0NzcuNjExNzE4SDEzODUuMDg4MzA5YTQ3Ljc2MTE3MiA0Ny43NjExNzIgMCAwIDEgMCA5NS41MjIzNDN6TTExMDQuMjUyNjE5IDEwMjMuOTk5NTIyYTE5OC4yMDg4NjMgMTk4LjIwODg2MyAwIDEgMSAxOTcuNzMxMjUxLTE5OC4yMDg4NjIgMTk4LjY4NjQ3NCAxOTguNjg2NDc0IDAgMCAxLTE5Ny43MzEyNTEgMTk4LjIwODg2MnogbTAtMzAwLjg5NTM4MmExMDIuNjg2NTE5IDEwMi42ODY1MTkgMCAxIDAgMTAyLjIwODkwOCAxMDIuNjg2NTIgMTAyLjY4NjUxOSAxMDIuNjg2NTE5IDAgMCAwLTEwMi4yMDg5MDgtMTAyLjY4NjUyek0zNzUuNDE3MTM4IDEwMjMuOTk5NTIyQTE5OC4yMDg4NjMgMTk4LjIwODg2MyAwIDEgMSA1NzMuMTQ4Mzg5IDgyNS43OTA2NmExOTguNjg2NDc0IDE5OC42ODY0NzQgMCAwIDEtMTk3LjczMTI1MSAxOTguMjA4ODYyeiBtMC0zMDAuODk1MzgyQTEwMi42ODY1MTkgMTAyLjY4NjUxOSAwIDEgMCA0NzcuNjI2MDQ2IDgyNS43OTA2NmExMDIuNjg2NTE5IDEwMi42ODY1MTkgMCAwIDAtMTAyLjIwODkwOC0xMDIuNjg2NTJ6IiBmaWxsPSIjZDgxZTA2IiBwLWlkPSIxMjI4Ij48L3BhdGg+PHBhdGggZD0iTTc4Mi4zNDIzMjIgODc1LjkzOTg5SDUyNS4zODcyMThhNDcuNzYxMTcyIDQ3Ljc2MTE3MiAwIDAgMSAwLTk1LjUyMjM0NGgyNTYuNDc3NDkyYTQ3Ljc2MTE3MiA0Ny43NjExNzIgMCAwIDEgMCA5NS41MjIzNDR6IiBmaWxsPSIjZDgxZTA2IiBwLWlkPSIxMjI5Ij48L3BhdGg+PHBhdGggZD0iTTIyNC45Njk0NDcgODc1LjkzOTg5SDEyNi41ODE0MzRBMTI2LjU2NzEwNSAxMjYuNTY3MTA1IDAgMCAxIDAuMDE0MzI4IDc0OS4zNzI3ODV2LTYyMC44OTUyMzNBMTI2LjU2NzEwNSAxMjYuNTY3MTA1IDAgMCAxIDEyNi41ODE0MzQgMGg2NTUuNzYwODg4QTEyNi41NjcxMDUgMTI2LjU2NzEwNSAwIDAgMSA5MDcuNDc2NTkyIDEyNi41NjcxMDV2Njk5LjIyMzU1NWE0Ny43NjExNzIgNDcuNzYxMTcyIDAgMCAxLTk1LjUyMjM0NCAwVjEyNi41NjcxMDVhMzEuMDQ0NzYyIDMxLjA0NDc2MiAwIDAgMC0yOS42MTE5MjYtMzEuMDQ0NzYxSDEyNi41ODE0MzRhMzEuMDQ0NzYyIDMxLjA0NDc2MiAwIDAgMC0zMS4wNDQ3NjIgMzEuMDQ0NzYxdjYyMC44OTUyMzNhMzEuMDQ0NzYyIDMxLjA0NDc2MiAwIDAgMCAzMS4wNDQ3NjIgMzEuMDQ0NzYyaDk4LjM4ODAxM2E0Ny43NjExNzIgNDcuNzYxMTcyIDAgMSAxIDAgOTUuNTIyMzQzeiIgZmlsbD0iI2Q4MWUwNiIgcC1pZD0iMTIzMCI+PC9wYXRoPjwvc3ZnPg==');
    background-position: 50%;
    background-repeat: no-repeat;
    display: inline-block;
  }
  
  .mapboxgl-marker {
    cursor: pointer;
  }
</style>
