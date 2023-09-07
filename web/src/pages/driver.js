import RidersClient from '../api/ridersClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the manageInventory page of the website.
 */
const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};

class Driver extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit','searchtrips','displaydriverSearchResults','getHTMLFordriverSearchResults','selectDriverTrip','unselectDriverTrip'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaydriverSearchResults);
        }

        /**
             * Add the header to the page and load the MusicPlaylistClient.
             */

           mount() {;

               this.header.addHeaderToPage();

               this.client = new RidersClient();
               document.getElementById('search-trip').addEventListener('click', this.searchtrips);
           }

           /**
            * Method to run when the add driver submit button is pressed. Call Client to create the
            * new driver.
            */
            async submit(evt) {
                    evt.preventDefault();
                }
            async searchtrips() {
                    const currentUser = await this.client.getIdentity();
                    const zipcodevalue = document.getElementById('zipcodetext').value;
                    const previouszipcode = this.dataStore.get(SEARCH_CRITERIA_KEY);
                    if (zipcodevalue){
                    const results = await this.client.searchdriverTrips(zipcodevalue,console.log);
                    console.log(`this the trip : ${results}`);
                    this.dataStore.setState({
                        [SEARCH_CRITERIA_KEY]: zipcodevalue,
                        [SEARCH_RESULTS_KEY]: results,
                    });
                }
                else{
                    this.dataStore.setState(EMPTY_DATASTORE_STATE);
                }
            }

            displaydriverSearchResults() {
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
                        searchResultsDisplay.innerHTML = this.getHTMLFordriverSearchResults(searchResults);
                        document.querySelectorAll(".btn").forEach(btn =>
                            btn.addEventListener("click",this.selectDriverTrip)
                            );
                        document.querySelectorAll(".unbtn").forEach(unbtn =>
                            unbtn.addEventListener("click",this.unselectDriverTrip)
                            );
                        console.log(searchResults)
                    }
                }

           getHTMLFordriverSearchResults(searchResults) {
                    if (searchResults.length === 0) {
                        return '<h4>No results found</h4>';
                    }
                    let html = '<table><tr><th>Pickup date time</th><th>Pickup Street address</th><th>Pickup city</th><th>Pickup zip Code</th><th>Drop off Street address</th><th>Drop off zip code</th><th>Mobility</th><th>Selected status</th><th>driver Email</th><th>Select trip</th><th>Unselect trip</th></tr>';
                    for (let i =0 ;i<searchResults.length;i++) {
                        html += `
                        <tr>
                            <td>${searchResults[i].pickupDateTime}</td>
                            <td>${searchResults[i].pickupStreetAddress}</td>
                            <td>${searchResults[i].pickupCity}</td>
                            <td>${searchResults[i].pickupZipCode}</td>
                            <td>${searchResults[i].dropOffStreetAddress}</td>
                            <td>${searchResults[i].dropOffZipCode}</td>
                            <td>${searchResults[i].mobility}</td>
                            <td>${searchResults[i].selected}</td>
                            <td>${searchResults[i].driverId}</td>
                            <td><button class ="btn" data-tripid='${searchResults[i].tripId}' data-memberid='${searchResults[i].memberId}'>select</button></td>
                            <td><button class ="unbtn" data-tripid='${searchResults[i].tripId}' data-memberid='${searchResults[i].memberId}'>unselect</button></td>
                        </tr>`;
                    }
                    html += '</table>';
                    return html;
                }
            async selectDriverTrip(tripinfo)
                {
                    tripinfo.preventDefault();
                    const currentTripid=tripinfo.target.dataset.tripid;
                    const currentMemeberid=tripinfo.target.dataset.memberid;
                    const currentUser = await this.client.getIdentity();
                    console.log(currentUser);
                    const selectStatus='YES';
                    const updateResult = await this.client.updatedriverTrip(currentTripid,currentMemeberid,currentUser.email,selectStatus,console.log);
                    this.searchtrips();
                }
            async unselectDriverTrip(tripinfo)
                {
                    tripinfo.preventDefault();
                    const currentTripid=tripinfo.target.dataset.tripid;
                    const currentMemeberid=tripinfo.target.dataset.memberid;
                    const currentDriver = 'Not Selected'
                    const selectStatus='NO';
                    const updateResult = await this.client.updatedriverTrip(currentTripid,currentMemeberid,currentDriver,selectStatus,console.log);
                    this.searchtrips();
                }

            }

            /**
             * Main method to run when the page contents have loaded.
             */
            const main = async () => {
                const driver = new Driver();
                driver.mount();
            };

            window.addEventListener('DOMContentLoaded', main);



