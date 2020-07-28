import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export const routes = [
  {
    path: '/',
    component: () => import('@/pages/Covid')
  },
  {
    path: '/weather',
    component: () => import('@/pages/Weather')
  },
  {
    path: '/delivery',
    component: () => import('@/pages/Delivery')
  },
  {
    path: '/navigation',
    component: () => import('@/pages/Navigation')
  },
  {
    path: '/user',
    component: () => import('@/pages/User')
  },
  {
    path: '/role',
    component: () => import('@/pages/Role')
  },
  {
    path: '/site',
    component: () => import('@/pages/Site')
  },
  {
    path: '/material',
    component: () => import('@/pages/Material')
  },
  {
    path: '/transfer',
    component: () => import('@/pages/Transfer')
  }
  
  
];
const router = new Router({
  routes // (缩写) 相当于 routes: routes
});

export default router;
