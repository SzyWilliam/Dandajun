import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import main from '@/components/main'

Vue.use(Router)
export const router = new Router({
  routes:[
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/main',
      name: "MainPage",
      component: main
    }
  ]
})
