<template>
  <div>
    <h2>Donor List: {{this.$route.params.name}}</h2>
    <h4>Empfängerkonto: {{this.$route.params.account.name}}, {{this.$route.params.account.iban}}, {{this.$route.params.account.nameOfBank}}</h4>
    <b-table small striped hover :fields="fields" :items="donations"></b-table>
    <router-link :to="'./listCampaigns'">
      <b-button>Go back</b-button>
    </router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fields: [
        "donorName",
        {
          key: "account.iban",
          label: "IBAN"
        },
        {
          key: "account.nameOfBank",
          label: "Name of Bank"
        },
        "amount",
        "receiptRequested",
        "status"
      ],
      donations: []
    };
  },
  mounted() {
    fetch(
      "http://localhost:8080/rest/organizer/donation/list/" +
        this.$route.params.campaignId,
      {
        method: "get"
      }
    )
      .then(response => {
        return response.json();
      })
      .then(jsonData => {
        this.donations = jsonData;
      });
  }
};
</script>
