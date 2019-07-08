<template>
  <div>
    <h1>Edit Donation Form</h1>
    <b-row class="my-1">
      <b-col sm="2">
        <label for="bgColor">Background color:</label>
      </b-col>
      <b-col sm="1">
        <b-form-input id="bgColor" type="color" v-model="bgColor" @input="generateLink"></b-form-input>
      </b-col>
    </b-row>

    <b-row class="my-1">
      <b-col sm="2">
        <label for="fontColor">Font color:</label>
      </b-col>
      <b-col sm="1">
        <b-form-input id="fontColor" type="color" v-model="fontColor" @input="generateLink"></b-form-input>
      </b-col>
    </b-row>

    <b-row>
      <b-col sm="2">
        <label for="url">URL of formular:</label>
      </b-col>
      <b-col sm="3">
        <b-form-textarea id="url" size="sm" type="url" v-model="donationLink"></b-form-textarea>
      </b-col>
    </b-row>

    <router-link :to="'./listCampaigns'">
      <b-button>Go back</b-button>
    </router-link>
  </div>
</template>

<script>
export default {
  data () {
    return {
      campaignId: '',
      rootLink: 'http://localhost:8081/donateMoney',
      bgColor: '#ffffff',
      fontColor: '#000000',
      donationLink: ''
    }
  },
  methods: {
    generateLink () {
      console.log(this.fontColor)
      this.donationLink = this.rootLink + '?bgColor=' + this.bgColor.substring(1, 7) + '&textColor=' + this.fontColor.substring(1, 7) + '&campaignId=' + this.campaignId
    }
  },
  mounted () {
    this.campaignId = this.$route.params.campaignId
    this.generateLink()
  }
}
</script>
