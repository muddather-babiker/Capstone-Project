package capstone.project.dependency;

import capstone.project.activity.*;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    AddDriverActivity provideAddDriverActivity();
    AddMemberActivity provideAddMemberActivity();
    AddTripActivity provideAddTripActivity();
    RemoveTripActivity provideRemoveTripActivity();
    GetTripActivity provideGetTripActivity();
    SearchTripsActivity provideSearchTripsActivity();
    SearchTripsDriverActivity provideSearchTripsDriverActivity();
    UpdateTripActivity provideUpdateTripActivity();
    UpdateTripMemberActivity provideUpdateTripMemberActivity();
    GetDriverActivity provideGetDriverActivity();
    GetMemberActivity provideGetMemberActivity();
}
