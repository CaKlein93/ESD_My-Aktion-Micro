<template>
  <div>
    <h1>Edit campaign</h1>
    <b-card no-body>
      <b-tabs pills card vertical>
        <b-tab title="General" active>
          <b-card-text>
            <b-container>
              <b-row class="my-1">
                <b-col sm="3">
                  <label for="name">Name</label>
                </b-col>
                <b-col sm="9">
                  <b-form-input id="name"
                                v-model="campaign.name"
                                placeholder="Name of the campaign"
                                type="text"
                                :state="nameState"></b-form-input>
                </b-col>
              </b-row>
              <b-row class="my-1">
                <b-col sm="3">
                  <label for="target">Target amount</label>
                </b-col>
                <b-col sm="9">
                  <b-form-input id="target"
                                v-model="campaign.targetAmount"
                                placeholder="Target amount"
                                type="number"
                                :state="targetState"></b-form-input>
                </b-col>
              </b-row>
              <b-row class="my-1">
                <b-col sm="3">
                  <label for="donationMin">Donation minimum</label>
                </b-col>
                <b-col sm="9">
                  <b-form-input id="donationMin"
                                v-model="campaign.donationMinimum"
                                placeholder="Donation minimum"
                                type="number"
                                :state="donationMinState"></b-form-input>
                </b-col>
              </b-row>
            </b-container>
          </b-card-text>
        </b-tab>

        <b-tab title="Bank information">
          <b-card-text>
            <b-container>
              <b-row class="my-1">
                <b-col sm="3">
                  <label for="name">Name</label>
                </b-col>
                <b-col sm="9">
                  <b-form-input id="name"
                                v-model="campaign.account.name"
                                placeholder="Name of receipient"
                                type="text"
                                :state="receipientState"></b-form-input>
                </b-col>
              </b-row>
              <b-row class="my-1">
                <b-col sm="3">
                  <label for="iban">IBAN</label>
                </b-col>
                <b-col sm="9">
                  <b-form-input id="iban"
                                v-model="campaign.account.iban"
                                placeholder="IBAN"
                                type="text"
                                :state="ibanState"></b-form-input>
                </b-col>
              </b-row>
              <b-row class="my-1">
                <b-col sm="3">
                  <label for="nameOfBank">Name of bank</label>
                </b-col>
                <b-col sm="9">
                  <b-form-input id="nameOfBank"
                                v-model="campaign.account.nameOfBank"
                                placeholder="Name of receivers bank"
                                type="text"
                                :state="bankNameState"></b-form-input>
                </b-col>
              </b-row>
            </b-container>
          </b-card-text>
        </b-tab>
      </b-tabs>
    </b-card>
    <router-link to="/listCampaigns">
      <b-button variant="success" @click="updateCampaign">Save</b-button>
      <b-button variant="danger">Cancel</b-button>
    </router-link>
  </div>
</template>

<script>
export default {
  computed: {
    nameState() {
      return this.campaign.name.length >= 5 ? true : false;
    },
    targetState() {
      return this.campaign.targetAmount >= 250 ? true : false;
    },
    donationMinState() {
      return this.campaign.donationMinimum >= 10 ? true : false;
    },
    receipientState() {
      return this.campaign.account.name.length >= 7 ? true : false;
    },
    ibanState() {
      return this.campaign.account.iban.length == 22 ? true : false;
    },
    bankNameState() {
      return this.campaign.account.nameOfBank.length >= 6 ? true : false;
    }
  },

  data() {
    return {
      campaign: {
        account: {
          iban: '',
          name: '',
          nameOfBank: ''
        },
        name: '',
        donationMinimum: '',
        targetAmount: ''
      },
      campaignId: '',
      createNew: false
    }
  },
  methods: {
    updateCampaign() {
	
	if(this.createNew){
		this.createCampaign
	}else{
      fetch('http://localhost:8080/rest/organizer/campaign/' + this.campaign.id, {
        method: 'put',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.campaign)
      })
    }},
    createCampaign() {
      fetch('http://localhost:8080/rest/organizer/campaign', {
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.campaign)
      }.then(response => {
        console.log(response.status)
      })
      )
    }
  },
  mounted: function() {
    if (Object.keys(this.$route.params).length != 0) {
      this.campaign.account = this.$route.params.campaign.account;
      this.campaign.name = this.$route.params.campaign.name;
      this.campaign.donationMinimum = this.$route.params.campaign.donationMinimum;
      this.campaign.targetAmount = this.$route.params.campaign.targetAmount;

      this.campaignId = this.$route.params.campaign.campaignId;
    } else {
      this.createNew = true;
    }
  }
}
</script>

<style scoped>
.btn {
  margin: 0 5px;
}
</style>
