package toast.appback.src.users.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toast.appback.src.shared.application.ApplicationEventBus;
import toast.appback.src.shared.application.DomainEventBus;
import toast.appback.src.users.application.usecase.implementation.AddFriendUseCase;
import toast.appback.src.users.application.usecase.implementation.CreateUserUseCase;
import toast.appback.src.users.application.usecase.implementation.EditUserUseCase;
import toast.appback.src.users.application.usecase.implementation.RemoveFriendUseCase;
import toast.appback.src.users.domain.repository.FriendShipRepository;
import toast.appback.src.users.domain.repository.UserRepository;

@Configuration
public class UserUseCasesConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(
            UserRepository userRepository
    ) {
        return new CreateUserUseCase(
                userRepository
        );
    }

    @Bean
    public AddFriendUseCase addFriendUseCase(
            FriendShipRepository friendShipRepository,
            UserRepository userRepository,
            DomainEventBus domainEventBus
    ) {
        return new AddFriendUseCase(
                friendShipRepository,
                userRepository,
                domainEventBus
        );
    }

    @Bean
    public RemoveFriendUseCase removeFriendUseCase(
            FriendShipRepository friendShipRepository,
            ApplicationEventBus applicationEventBus
    ) {
        return new RemoveFriendUseCase(
                friendShipRepository,
                applicationEventBus
        );
    }

    @Bean
    public EditUserUseCase editUserUseCase(
            UserRepository userRepository
    ) {
        return new EditUserUseCase(
                userRepository
        );
    }
}
