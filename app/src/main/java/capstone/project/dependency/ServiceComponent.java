package capstone.project.dependency;

import capstone.project.activity.AddDriverActivity;
import capstone.project.activity.AddMemberActivity;
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
}
