import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import Apply from '../components/Apply'
import Index from '../components/Index'
import Admin from '../components/Admin'
import Error from '../components/Error'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
    },
    {
      path:'/home',
      name: 'Home',
      component: Home,
      redirect: '/index',
      children:[
        {
          path: '/index',
          name: 'Index',
          component: Index,
        },
        {
          path: '/apply',
          name: 'Apply',
          component: Apply,
          hidden: true,
            meta: {
              keepAlive: false
            }
        },
        {
          path: '/admin',
          name: 'Admin',
          component: Admin,
          hidden: true,
            meta: {
              keepAlive: false
            }
        }
      ]
    },
    {
      path: '/error',
      name: 'Error',
      component: Error,
    },
  ]
})


