import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the Riderservice.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class RidersClient extends BindingClass {
    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout','addDriver','addMember','addTrip','searchAllTrips','searchBYDateTrips','updatedriverTrip','updateTrip'];
        this.bindClassMethods(methodsToBind, this);
        this.authenticator = new Authenticator();;
        this.props = props;
        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }
    /**
     * add a new driver .
     * @param driverEmail The driver Email of the diver to add.
     * @param name The driver name of the diver to add.
     * @param dateOfBirth The driver birth date of the diver to add.
     * @param phoneNumber The driver phone number of the diver to add.
     * @param streetAddress The driver home street address of the diver to add.
     * @param city The driver home city of the diver to add.
     * @param state The driver home state of the diver to add.
     * @param zipCode The driver home zip code of the diver to add.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The Driver.
     */
    async addDriver(driverEmail, name, dateOfBirth, phoneNumber, streetAddress, city, state, zipCode, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create a new driver.");
            const response = await this.axiosClient.post(`drivers`, {
                driverEmail: driverEmail,
                name: name,
                dateOfBirth: dateOfBirth,
                phoneNumber: phoneNumber,
                streetAddress: streetAddress,
                city: city,
                state: state,
                zipCode: zipCode
                }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.driver;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    /**
     * Get the driver by the email.
     * @param email the driver email.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns driver.
     */
    async getDriver(email,errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create a new driver.");
            const response = await this.axiosClient.get(`drivers/?driverEmail=${email}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.driverModel;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    /**
     * Get the member by the email.
     * @param email the member email.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns member.
     */
    async getMember(email,errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create a new driver.");
            const response = await this.axiosClient.get(`members/?memberEmail=${email}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.memberModel;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    /**
     * Get a trip by the tripId and the memberID.
     * @param tripId Unique identifier for the trip.
     * @param email the member email.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns trip.
     */
    async getTrip(tripId,email,errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get trips");
            const response = await this.axiosClient.get(`trips/?tripId=${tripId}&memberId=${email}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.tripModel;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    /**
     * Search for a trip.
     * @param memberId the member email.
     * @returns The triplists that match the member email.
     */
    async searchAllTrips(memberId,errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get trips");
            const response = await this.axiosClient.get(`trips/search?memberId=${memberId}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.trips;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    /**
     * Search for a trip.
     * @param zipcode the trip pickup Zip Code.
     * @returns The triplists that match the pickup Zip Code.
     */
    async searchdriverTrips(zipcode,errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get trips");
            const response = await this.axiosClient.get(`trips/search/driver?pickupZipCode=${zipcode}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.trips;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async searchBYDateTrips(memberId,pickupDateTime,errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get trips");
            const response = await this.axiosClient.get(`trips/search?memberId=${memberId}&pickupDateTime=${pickupDateTime}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.trips;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    /**
     * delete the trip by the tripId and memberId.
     * @param tripId Unique identifier for the trip.
     * @param email the member email.
     * @returns trip.
     */
    async deleteTrip(tripId,email,errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete trips");
            const response = await this.axiosClient.delete(`trips/?tripId=${tripId}&memberId=${email}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.tripModel;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    /**
     * add a new member .
     * @param memberEmail The member Email of the member to add.
     * @param name The member name of the member to add.
     * @param dateOfBirth The member birth date of the member to add.
     * @param phoneNumber The member phone number of the member to add.
     * @param streetAddress The member home street address of the member to add.
     * @param city The member home city of the member to add.
     * @param state The member home state of the member to add.
     * @param zipCode The member home zip code of the member to add.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The Driver.
     */
    async addMember(memberEmail, name, dateOfBirth, phoneNumber, streetAddress, city, state, zipCode, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create a new member.");
            const response = await this.axiosClient.post(`members`, {
                memberEmail: memberEmail,
                name: name,
                dateOfBirth: dateOfBirth,
                phoneNumber: phoneNumber,
                streetAddress: streetAddress,
                city: city,
                state: state,
                zipCode: zipCode
                }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.memberModel;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async updatedriverTrip(tripId,memberId,driverId,selectStatus,errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update trips.");
            const response = await this.axiosClient.put(`trips`, {
                tripId: tripId,
                memberId: memberId,
                driverId: driverId,
                selected:selectStatus
            },{
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.trip;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    async addTrip(tripId, memberId, pickupDateTime, pickupStreetAddress, pickupCity, pickupState, pickupZipCode, dropOffStreetAddress, dropOffCity, dropOffState, dropOffZipCode, mobility,selected,driverId, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated member can create a new trip.");
            const response = await this.axiosClient.post(`trips`, {
                tripId: tripId,
                memberId: memberId,
                pickupDateTime: pickupDateTime,
                pickupStreetAddress: pickupStreetAddress,
                pickupCity: pickupCity,
                pickupState: pickupState,
                pickupZipCode: pickupZipCode,
                dropOffStreetAddress: dropOffStreetAddress,
                dropOffCity: dropOffCity,
                dropOffState: dropOffState,
                dropOffZipCode: dropOffZipCode,
                mobility: mobility,
                selected:selected,
                driverId:driverId
                }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.tripModel;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }
    
    async updateTrip(tripId, memberId, pickupDateTime, pickupStreetAddress, pickupCity, pickupState, pickupZipCode, dropOffStreetAddress, dropOffCity, dropOffState, dropOffZipCode, mobility,errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated member can update a trip.");
            const response = await this.axiosClient.put(`trips/member`, {
                tripId: tripId,
                memberId: memberId,
                pickupDateTime: pickupDateTime,
                pickupStreetAddress: pickupStreetAddress,
                pickupCity: pickupCity,
                pickupState: pickupState,
                pickupZipCode: pickupZipCode,
                dropOffStreetAddress: dropOffStreetAddress,
                dropOffCity: dropOffCity,
                dropOffState: dropOffState,
                dropOffZipCode: dropOffZipCode,
                mobility: mobility
                }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.trip;
        }
        catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);
        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}
