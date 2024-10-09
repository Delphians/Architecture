import SwiftUI
import app

@main
struct iOSApp: App {
    init() {
       KoinHelperKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}