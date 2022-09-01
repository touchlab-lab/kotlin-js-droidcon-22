package shaktiman.droid.nydroidcon22

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
internal expect val ApplicationDispatcher: CoroutineDispatcher
