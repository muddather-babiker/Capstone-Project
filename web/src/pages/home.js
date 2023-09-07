import RidersClient from '../api/ridersClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};

class Home extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'getRole','redirecToAddDriver','redirecToAddMember'], this);

        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
        console.log("searchPlaylists constructor");
    }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new RidersClient();
        const currentUser = await this.client.getIdentity();
        console.log(` this the current user ${currentUser}`);
        if (currentUser){
            this.getRole(currentUser.email);
        }
        else {
            document.getElementById('newRole').classList.add('hidden');

        }

    }

    async getRole(currentUserEmail) {
        const driver = await this.client.getDriver(currentUserEmail,console.log);
        console.log(`this the new driver ${driver.driverEmail}`);
        if (driver.driverEmail!==null){
            console.log(`the driver email ${driver.driverEmail}`);
            window.location.href='driver.html';
        }
        else {
            const member = await this.client.getMember(currentUserEmail,console.log);
            console.log(`this the new member ${member}`);
            if (member.memberEmail!==null){
                console.log(member.memberEmail);
               window.location.href='member.html';
            }
            else {
                const marqueeLable=document.getElementById('marqueeLabletext');
                marqueeLable.innerText='Please Register as a New Member Or a New Driver by pressing the buttons the on the left bottom corner';
                marqueeLable.style.color='#EE3A23';
                document.getElementById('newRole').classList.remove('hidden');
            }

        }
    }

    redirecToAddDriver() {
        window.location.href='addDriver.html';    
    }

    redirecToAddMember() {
        window.location.href='addMember.html';    
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const home = new Home();
    home.mount();
};

window.addEventListener('DOMContentLoaded', main);
