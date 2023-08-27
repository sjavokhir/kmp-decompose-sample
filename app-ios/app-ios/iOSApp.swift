import SwiftUI
import shared

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    @Environment(\.scenePhase)
    var scenePhase: ScenePhase

    var rootHolder: RootHolder { appDelegate.getRootHolder() }

    var body: some Scene {
        WindowGroup {
            RootView(rootHolder.root)
                .onChange(of: scenePhase) { newPhase in
                    switch newPhase {
                    case .background: LifecycleRegistryExtKt.stop(rootHolder.lifecycle)
                    case .inactive: LifecycleRegistryExtKt.pause(rootHolder.lifecycle)
                    case .active: LifecycleRegistryExtKt.resume(rootHolder.lifecycle)
                    @unknown default: break
                    }
                }
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    private var rootHolder: RootHolder?
    
    func application(_ application: UIApplication, shouldSaveSecureApplicationState coder: NSCoder) -> Bool {
        return false
    }
    
    func application(_ application: UIApplication, shouldRestoreSecureApplicationState coder: NSCoder) -> Bool {
        return false
    }
    
    fileprivate func getRootHolder() -> RootHolder {
        if (rootHolder == nil) {
            rootHolder = RootHolder()
        }
        
        return rootHolder!
    }
}
