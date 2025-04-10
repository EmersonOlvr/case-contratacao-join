import { useState } from "react";
import { Alert, AlertColor, Collapse, Fade, IconButton } from "@mui/material";
import { Close as CloseIcon } from "@mui/icons-material";

interface AlertOptions {
    type: AlertColor,
    message: React.ReactNode,
    closeable?: boolean,
    disableAnimation?: boolean,
    animation?: "collapse" | "fade",
}

const useAlert = () => {
    const [open, setOpen] = useState<boolean>(false);
    const [alertOptions, setAlertOptions] = useState<AlertOptions | null>(null);

    const showAlert = (alertOptions: AlertOptions) => {
        setAlertOptions(alertOptions);
        setOpen(true);
    };

    const closeAlert = () => {
        setOpen(false);
    };

    const AlertComponent = (
        <Alert
            severity={alertOptions?.type}
            action={
                alertOptions?.closeable ? (
                    <IconButton
                        aria-label="close"
                        color="inherit"
                        size="small"
                        onClick={closeAlert}
                    >
                        <CloseIcon fontSize="inherit" />
                    </IconButton>
                ) : undefined
            }
            sx={{ mb: 1, wordBreak: "break-word" }}
        >
            {alertOptions?.message}
        </Alert>
    );

    const AnimatedAlertComponent = (
        alertOptions?.animation === "fade" ? (
            <Fade in={open} unmountOnExit>
                {AlertComponent}
            </Fade>
        ) : (
            <Collapse in={open} unmountOnExit>
                {AlertComponent}
            </Collapse>
        )
    );

    return {
        showAlert,
        closeAlert,
        AlertComponent: alertOptions?.disableAnimation ? AlertComponent : AnimatedAlertComponent,
    };
};

export default useAlert;