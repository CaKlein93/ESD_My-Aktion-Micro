<template>
  <div class="home">
    <b-container>
      <b-row class="my-1">
        <b-col sm="3">
          <label>Email:</label>
        </b-col>
        <b-col sm="9">
          <b-form-input id="email" type="email" v-model="email"></b-form-input>
        </b-col>
      </b-row>

      <b-row class="my-1">
        <b-col sm="3">
          <label>Password:</label>
        </b-col>
        <b-col sm="9">
          <b-form-input id="password" type="password" v-model="password"></b-form-input>
        </b-col>
      </b-row>
      <b-button variant="success" @click="getSecurityToken">Sign in</b-button>
    </b-container>
  </div>
</template>

<script>
import { router } from "../router";

export default {
  data() {
    return {
      email: "",
      password: "",
      returnUrl: "listCampaigns"
    };
  },
  created() {
    //localStorage.removeItem('token')
    this.returnUrl = this.$route.query.returnUrl || "/listCampaigns";
  },
  methods: {
    getSecurityToken() {
      const request = new Request(
        "https://1b249c14-5e91-493e-b5c4-2443d446e333.mock.pstmn.io/getSecurityToken",
        {
          method: "post",
          headers: new Headers({
            Authorization: "Basic " + btoa(this.email + ":" + this.password)
          })
        }
      );
      // fetch(request).then(response => {
      //   if (!response.ok) {
      //     if (response.status === 401) {
      //       localStorage.removeItem("token");
      //       location.reload(true);
      //     }
      //   } else {
      //     response.text().then(body => {
      //       localStorage.setItem("token", body);
      //       router.push(this.returnUrl);
      //     });
      //   }
      // });
      router.push(this.returnUrl)
    }
  }
};
</script>
