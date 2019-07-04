package social.alone.server.push;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
class FcmTokenRequest {

    @Valid @NotEmpty
    String fcmToken;
}
