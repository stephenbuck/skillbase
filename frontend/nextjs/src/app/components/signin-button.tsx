import { signIn } from "@/auth.ts"
 
export function SignIn() {
  return (
    <form
      action={async () => {
        "use server"
        await signIn("github", { redirectTo: "/dashboard" })
      }}
    >
      <button type="submit">Sign in</button>
    </form>
  )
}
