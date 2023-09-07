import RidersClient from '../api/ridersClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};

class Member extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount','findTripmenu','allTrip','searchtrip','findBYConfimation','findBYDate','displaySearchResults','getHTMLForSearchResults','deleteTripmenu','updateTripmenu','cancel'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
        }

           mount() {

               this.header.addHeaderToPage();
               this.client = new RidersClient();
               document.getElementById('findTripmenu').addEventListener('click', this.findTripmenu);
               document.getElementById('cancel').addEventListener('click', this.cancel);
               document.getElementById('deleteTripmenu').addEventListener('click', this.deleteTripmenu);
               document.getElementById('updateTripmenu').addEventListener('click', this.updateTripmenu);
               document.getElementById('allTrip').addEventListener('click', this.allTrip);
               document.getElementById('search-trip').addEventListener('click', this.searchtrip);
               document.getElementById('BYConfimation').addEventListener('click', this.findBYConfimation);
               document.getElementById('BYDate').addEventListener('click', this.findBYDate);
               document.getElementById('div-findtrip').classList.add('hidden');
               document.getElementById('div-findtrip-confirmation').classList.add('hidden');
               document.getElementById('div-findtrip-date').classList.add('hidden');
               document.getElementById('buttongroups').classList.add('hidden');
               const errorMessageDisplay = document.getElementById('error-message');
               errorMessageDisplay.innerText = ``;
               errorMessageDisplay.classList.add('hidden');
               const successfulMessageDisplay = document.getElementById('successful-message');
               successfulMessageDisplay.innerText = ``;
               successfulMessageDisplay.classList.add('hidden');
           }

            async findTripmenu() {
                    document.getElementById('div-findtrip').classList.remove('hidden');
                    document.getElementById('buttongroups').classList.add('hidden');
                    document.getElementById('search-trip').innerHTML="Search";
                    document.getElementById('div-findtrip-confirmation').classList.add('hidden');
                    document.getElementById('BYConfimation').checked=false;
                    document.getElementById('BYDate').checked=false;
                    document.getElementById('allTrip').checked=false;
                    document.getElementById('error-message').classList.add('hidden');
                    document.getElementById('successful-message').classList.add('hidden');
                }
            async deleteTripmenu() {
                    document.getElementById('div-findtrip-confirmation').classList.remove('hidden');
                    document.getElementById('buttongroups').classList.remove('hidden');
                    document.getElementById('search-trip').innerHTML="delete";
                    document.getElementById('div-findtrip').classList.add('hidden');
                    document.getElementById('div-findtrip-date').classList.add('hidden');
                    document.getElementById('error-message').classList.add('hidden');
                    document.getElementById('successful-message').classList.add('hidden');
                }
            async updateTripmenu() {
                    document.getElementById('div-findtrip-confirmation').classList.remove('hidden');
                    document.getElementById('buttongroups').classList.remove('hidden');
                    document.getElementById('search-trip').innerHTML="update";
                    document.getElementById('div-findtrip').classList.add('hidden');
                    document.getElementById('div-findtrip-date').classList.add('hidden');
                    document.getElementById('error-message').classList.add('hidden');
                    document.getElementById('successful-message').classList.add('hidden');
                }
            async allTrip() {
                    document.getElementById('buttongroups').classList.remove('hidden');
                    document.getElementById('div-findtrip-date').classList.add('hidden');
                    document.getElementById('div-findtrip-confirmation').classList.add('hidden');
                }
            async cancel() {
                    document.getElementById('div-findtrip').classList.add('hidden');
                    document.getElementById('div-findtrip-confirmation').classList.add('hidden');
                    document.getElementById('div-findtrip-date').classList.add('hidden');
                    document.getElementById('buttongroups').classList.add('hidden');
                    const errorMessageDisplay = document.getElementById('error-message');
                    errorMessageDisplay.innerText = ``;
                    errorMessageDisplay.classList.add('hidden');
                    const successfulMessageDisplay = document.getElementById('successful-message');
                    successfulMessageDisplay.innerText = ``;
                    successfulMessageDisplay.classList.add('hidden');
                }
            async searchtrip() {
                    const errorMessageDisplay = document.getElementById('error-message');
                    errorMessageDisplay.innerText = ``;
                    errorMessageDisplay.classList.add('hidden');
                    const successfulMessageDisplay = document.getElementById('successful-message');
                    successfulMessageDisplay.innerText = ``;
                    successfulMessageDisplay.classList.add('hidden');
                    console.log('Hello')
                    const currentUser = await this.client.getIdentity();
                    const tripId=document.getElementById('tripId').value;
                    if (document.getElementById('search-trip').innerHTML==="delete"){
                        const deletedTrip = await this.client.deleteTrip(tripId,currentUser.email,(error) => {
                            errorMessageDisplay.innerText = `Error: ${error.message}`;
                            errorMessageDisplay.classList.remove('hidden');
                            });
                        console.log(deletedTrip);
                        if (deletedTrip.tripId){
                            successfulMessageDisplay.innerText =`Successfully deleted the trip with the trip id: ${deletedTrip.tripId}`;
                            successfulMessageDisplay.classList.remove('hidden');
                        }
                    }
                    else if (document.getElementById('search-trip').innerHTML==="update"){
                        const updateTrip = await this.client.getTrip(tripId,currentUser.email,(error) => {
                            errorMessageDisplay.innerText = `Error: ${error.message}`;
                            errorMessageDisplay.classList.remove('hidden');
                            });
                        if (updateTrip.tripId===null){

                        }
                        else {
                            window.location.href= `updateTrip.html?tripId=${updateTrip.tripId}&memberId=${updateTrip.memberId}`;
                        }
                    }
                    if (document.getElementById('allTrip').checked){
                        const results = await this.client.searchAllTrips(currentUser.email,console.log);
                            console.log(`this the trip : ${results}`);
                            this.dataStore.setState({
                                [SEARCH_CRITERIA_KEY]: currentUser.email,
                                [SEARCH_RESULTS_KEY]: results,
                            });
                        
                    }
                    else if (document.getElementById('BYDate').checked){
                        const pickupDateTime = document.getElementById('pickupDateTime').value;
                        const previouspickupDateTime = this.dataStore.get(SEARCH_CRITERIA_KEY);
                        if (previouspickupDateTime === pickupDateTime) {
                            return;
                        }
                        if (pickupDateTime) {
                        const results = await this.client.searchBYDateTrips(currentUser.email,pickupDateTime,console.log);
                            console.log(`this the trip : ${results}`);
                            this.dataStore.setState({
                                [SEARCH_CRITERIA_KEY]: pickupDateTime,
                                [SEARCH_RESULTS_KEY]: results,
                            });
                        
                    }
                     else {
                            this.dataStore.setState(EMPTY_DATASTORE_STATE);
                        }
                    }
                    else if (document.getElementById('BYConfimation').checked){
                        const tripId=document.getElementById('tripId').value;
                        const previoustripId = this.dataStore.get(SEARCH_CRITERIA_KEY);
                        if (previoustripId === tripId) {
                            return;
                        }
                
                        if (tripId) {
                            const results = await this.client.getTrip(tripId,currentUser.email,console.log);
                            console.log(`this the trip : ${results}`);
                            this.dataStore.setState({
                                [SEARCH_CRITERIA_KEY]: tripId,
                                [SEARCH_RESULTS_KEY]: results,
                            });
                        } else {
                            this.dataStore.setState(EMPTY_DATASTORE_STATE);
                        }
                        
                    }
                    

                }
                async findBYConfimation() {
                    document.getElementById('buttongroups').classList.remove('hidden');
                    document.getElementById('div-findtrip-date').classList.add('hidden');
                    document.getElementById('div-findtrip-confirmation').classList.remove('hidden');

                }
                async findBYDate() {
                    document.getElementById('buttongroups').classList.remove('hidden');
                    document.getElementById('div-findtrip-date').classList.remove('hidden');
                    document.getElementById('div-findtrip-confirmation').classList.add('hidden');

                }
                displaySearchResults() {
                    const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
                    const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
            
                    const searchResultsContainer = document.getElementById('search-results-container');
                    const searchCriteriaDisplay = document.getElementById('search-criteria-display');
                    const searchResultsDisplay = document.getElementById('search-results-display');
            
                    if (searchCriteria === '') {
                        searchResultsContainer.classList.add('hidden');
                        searchCriteriaDisplay.innerHTML = '';
                        searchResultsDisplay.innerHTML = '';
                    } else {
                        searchResultsContainer.classList.remove('hidden');
                        searchCriteriaDisplay.innerHTML = `"${searchCriteria}"`;
                        searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
                        console.log(searchResults)
                    }
                }
                getHTMLForSearchResults(searchResults) {
                    if (searchResults.length === 0) {
                        return '<h4>No results found</h4>';
                    }
                    else if (Array.isArray(searchResults)){
                    let html = '<table><tr><th>Pickup date time</th><th>Pickup Street address</th><th>Pickup city</th><th>Pickup zip Code</th><th>Drop off Street address</th><th>Drop Off City</th><th>Drop off zip code</th><th>Mobility</th><th> selected </th><th> driver Email </th></tr>';
                    for (const res of searchResults) {
                        html += `
                        <tr>
                            <td>${res.pickupDateTime}</td>
                            <td>${res.pickupStreetAddress}</td>
                            <td>${res.pickupCity}</td>
                            <td>${res.pickupZipCode}</td>
                            <td>${res.dropOffStreetAddress}</td>
                            <td>${res.dropOffCity}</td>
                            <td>${res.dropOffZipCode}</td>
                            <td>${res.mobility}</td>
                            <td>${res.selected}</td>
                            <td>${res.driverId}</td>
                        </tr>`;
                    }
                    html += '</table>';
                    return html;
                }
                else
                {
                    let html = '<table><tr><th>Pickup date time</th><th>Pickup Street address</th><th>Pickup city</th><th>Pickup zip Code</th><th>Drop off Street address</th><th>Drop Off City</th><th>Drop off zip code</th><th>Mobility</th><th> selected </th><th> driver Email </th></tr>' +
            '<tr><td>'+searchResults.pickupDateTime+ '</td>' +
             '<td>'+ searchResults.pickupStreetAddress + '</td>'  +
             '<td>'+ searchResults.pickupCity + '</td>'  +
             '<td>'+ searchResults.pickupZipCode + '</td>'  +
             '<td>'+ searchResults.dropOffStreetAddress + '</td>'  +
             '<td>'+ searchResults.dropOffCity + '</td>'  +
             '<td>'+ searchResults.dropOffZipCode + '</td>'  +
             '<td>'+ searchResults.mobility + '</td>'  +
             '<td>'+ searchResults.selected  + '</td>' +
             '<td>'+ searchResults.driverId + '</td></tr>'
              '</table>'; 
              return html; 
                }  
                }

            }

            /**
             * Main method to run when the page contents have loaded.
             */
            const main = async () => {
                const member = new Member();
                member.mount();
            };

            window.addEventListener('DOMContentLoaded', main);



