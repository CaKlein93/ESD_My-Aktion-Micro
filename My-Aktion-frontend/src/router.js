import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import EditCampaign from './views/organizer/EditCampaign.vue'
import ListCampaigns from './views/organizer/ListCampaigns.vue'
import EditDonationForm from './views/organizer/EditDonationForm.vue'
import ListDonations from './views/organizer/ListDonations.vue'
import DonationForm from './views/DonationForm.vue'

Vue.use(Router)

export const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
     {
       path: '/donateMoney',
       name: 'donationForm',
       component: DonationForm
     },
    {
      path: '/editCampaign',
      name: 'editCampaign',
      props: true,
      component: EditCampaign
    },
    {
      path: '/listCampaigns',
      name: 'listCampaigns',
      component: ListCampaigns
    },
    {
      path: '/editDonationForm',
      name: 'editDonationForm',
      component: EditDonationForm
    },
    {
      path: '/listDonations',
      name: 'listDonations',
      component: ListDonations
    },
    {
      path: '*',
      redirect: '/listCampaigns'
    }
  ]
})

// router.beforeEach((to, from, next) => {
//   const publicPages = ['/', '/donationForm'];
//   const authRequired = !publicPages.includes(to.path);
//   const loggedIn = localStorage.getItem('token');
//   if (authRequired && !loggedIn) {
//     return next({
//       path: '/',
//       query: { returnUrl: to.path }
//     });
//   }
//   next();
// })
