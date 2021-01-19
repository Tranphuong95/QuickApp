import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import message, {
  MessageState
} from 'app/entities/message/message.reducer';
// prettier-ignore
import editor, {
  EditorState
} from 'app/entities/editor/editor.reducer';
// prettier-ignore
import productest, {
  ProductestState
} from 'app/entities/productest/productest.reducer';
// prettier-ignore
import device, {
  DeviceState
} from 'app/entities/device/device.reducer';
// prettier-ignore
// import cartshop,{
//   CartShopState
// } from "app/products/shopcart/cart.reducer";

// prettier-ignore
import testuuid, {
  TestuuidState
} from 'app/entities/testuuid/testuuid.reducer';
// prettier-ignore
import productId, {
  ProductIdState
} from 'app/entities/product-id/product-id.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly message: MessageState;
  readonly editor: EditorState;
  readonly productest: ProductestState;
  readonly device: DeviceState;
  // readonly cartshop: CartShopState; //todo add 15/1
  readonly testuuid: TestuuidState;
  readonly productId: ProductIdState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  message,
  editor,
  productest,
  device,
  // cartshop, //todo add 15/1
  testuuid,
  productId,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
